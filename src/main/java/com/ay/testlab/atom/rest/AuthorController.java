package com.ay.testlab.atom.rest;

import com.ay.testlab.atom.author.Author;
import com.ay.testlab.atom.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;


@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity<?> insertAuthor(@RequestBody Author author, UriComponentsBuilder ucBuilder) {
        if(authorService.isAuthorExist(author)){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        authorService.insertAuthor(author);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/author/{id}").buildAndExpand(author.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<List<Author>> getAllAuthors(){
        List<Author> authors = authorService.getAllAuthors();
        if(authors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }
}
