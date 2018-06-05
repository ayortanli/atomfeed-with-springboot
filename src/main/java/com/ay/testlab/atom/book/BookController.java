package com.ay.testlab.atom.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @RequestMapping(value="/", method = RequestMethod.POST)
    public ResponseEntity<?> createBook(@RequestBody Book book, UriComponentsBuilder ucBuilder) {
        if(bookService.isBookExist(book)){
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
        bookService.insertBook(book);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/book/{id}").buildAndExpand(book.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
        Optional<Book> storedBook = bookService.getBook(id);
        if(!storedBook.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Book updatedBook = bookService.updateBook(id, book);
        return new ResponseEntity<Book>(updatedBook, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBook(@PathVariable("id") Long id) {
        Optional<Book> book = bookService.getBook(id);
        if(!book.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ResponseEntity<List<Book>> findAllBooks(){
        List<Book> books = bookService.getAllBooks();
        if(books.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public ResponseEntity<Book> findBook(@PathVariable("id") Long id){
        Optional<Book> book = bookService.getBook(id);
        if(!book.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Book>(book.get(), HttpStatus.OK);
    }
}
