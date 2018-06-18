package com.ay.testlab.atom.book;

import com.rometools.rome.feed.atom.Entry;
import com.rometools.rome.feed.atom.Feed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class BookAuthorRetriever {

    private final Logger log = LoggerFactory.getLogger(BookAuthorRetriever.class);

    Date lastModified;
    private RestTemplate restTemplate;
    private String url;
    private BookService bookService;

    @Autowired
    public BookAuthorRetriever(@Value("${authorService.feed.url}") String url, BookService bookService){
        this.bookService = bookService;
        this.lastModified = null;
        restTemplate = new RestTemplate();
        this.url = url;
    }

    @Scheduled(fixedDelay = 10000) //10 seconds interval
    public void pollForRetrieve(){
        log.info("Scheduled feed retriever task starting at: "+ new Date());
        HttpHeaders requestHeaders = new HttpHeaders();
        if (lastModified != null) {
            requestHeaders.set(HttpHeaders.IF_MODIFIED_SINCE,
                    DateTimeFormatter.RFC_1123_DATE_TIME.
                            withZone(ZoneId.systemDefault()).
                            format(Instant.ofEpochMilli(lastModified.getTime())));
        }
        HttpEntity<?> requestEntity = new HttpEntity(requestHeaders);
        ResponseEntity<Feed> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Feed.class);

        if (response.getStatusCode() != HttpStatus.NOT_MODIFIED) {
            Feed feed = response.getBody();
            for (Entry entry : feed.getEntries()) {
                if ((lastModified == null) || (entry.getUpdated().after(lastModified))) {
                    BookAuthor author= restTemplate
                            .getForEntity(entry.getContents().get(0).getSrc(), BookAuthor.class).getBody();
                    bookService.saveUpdateAuthor(author);
                }
            }
            if (response.getHeaders().getFirst(HttpHeaders.LAST_MODIFIED) != null) {
                lastModified = Date.from(Instant.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(response.getHeaders().getFirst(HttpHeaders.LAST_MODIFIED))));
                log.info("Last Modified Time change to: "+ lastModified);
            }
        } else {
            log.info("HttpStatus.NOT_MODIFIED");
        }
        log.info("Scheduled feed retriever task finished at: "+ new Date());
    }
}
