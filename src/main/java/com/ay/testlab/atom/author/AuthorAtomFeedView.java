package com.ay.testlab.atom.author;

import com.rometools.rome.feed.atom.*;
import com.rometools.rome.feed.synd.SyndPerson;
import org.springframework.web.servlet.view.feed.AbstractAtomFeedView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AuthorAtomFeedView extends AbstractAtomFeedView {


    private Date lastUpdateTime;

    public AuthorAtomFeedView(Date lastUpdateTime){
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    protected void buildFeedMetadata(Map<String, Object> model, Feed feed, HttpServletRequest request) {
        super.buildFeedMetadata(model, feed, request);
        feed.setId("tag:ay.testlab.com/atom/author");
        feed.setTitle("Author");
        List<Link> alternateLinks = new ArrayList<>();
        Link link = new Link();
        link.setRel("self");

        link.setHref(baseUrl(request) + "feed");
        alternateLinks.add(link);
        List<SyndPerson> authors = new ArrayList<SyndPerson>();
        Person person = new Person();
        person.setName("A. Y.");
        authors.add(person);
        feed.setAuthors(authors);

        feed.setAlternateLinks(alternateLinks);
        feed.setUpdated(lastUpdateTime);
        Content subtitle = new Content();
        subtitle.setValue("List of all authors");
        feed.setSubtitle(subtitle);
    }

    @Override
    protected List<Entry> buildFeedEntries(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<Entry> entries = new ArrayList<Entry>();
        List<Author> authorList = (List<Author>) model.get("authors");

        for (Author author : authorList) {
            Entry entry = new Entry();
            entry.setId("tag:ay.testlab.com/atom/author/" + Long.toString(author.getId()));
            entry.setUpdated(author.getLastUpdateTime());
            entry.setTitle("Author " + author.getId());
            List<Content> contents = new ArrayList<Content>();
            Content content = new Content();
            content.setSrc(baseUrl(request) + "author/" + Long.toString(author.getId()));
            content.setType("application/json");
            contents.add(content);
            entry.setContents(contents);
            Content summary = new Content();
            summary.setValue("This stores information about author " + author.getId());
            entry.setSummary(summary);
            entries.add(entry);
        }

        return entries;
    }

    private String baseUrl(HttpServletRequest request) {
        return String.format("%s://%s:%d%s/", request.getScheme(), request.getServerName(), request.getServerPort(),
                request.getContextPath());
    }
}
