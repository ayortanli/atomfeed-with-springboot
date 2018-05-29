package com.ay.testlab.atom.rest;

import com.ay.testlab.atom.author.Author;
import com.ay.testlab.atom.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService){
        this.authorService = authorService;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthor(@RequestBody Author author, UriComponentsBuilder ucBuilder) {
        if(authorService.isAuthorExist(author)){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        authorService.insertAuthor(author);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/author/{id}").buildAndExpand(author.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Author> updateAuthor(@PathVariable("id") Long id, @RequestBody Author author) {
        Optional<Author> storedAuthor = authorService.getAuthor(id);
        if(!storedAuthor.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Author updatedAuthor = authorService.updateAuthor(id, author);
        return new ResponseEntity<Author>(updatedAuthor, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAuthor(@PathVariable("id") Long id) {
        Optional<Author> author = authorService.getAuthor(id);
        if(!author.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<List<Author>> findAllAuthors(){
        List<Author> authors = authorService.getAllAuthors();
        if(authors.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Author>>(authors, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Author> findAuthor(@PathVariable("id") Long id){
        Optional<Author> author = authorService.getAuthor(id);
        if(!author.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Author>(author.get(), HttpStatus.OK);
    }
}
