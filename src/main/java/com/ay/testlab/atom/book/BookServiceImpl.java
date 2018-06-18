package com.ay.testlab.atom.book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class BookServiceImpl implements BookService {

    private final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);

    private BookRepository bookRepository;
    private BookAuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, BookAuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void insertBook(Book book) {
        bookRepository.save(book);
        log.info("Book inserted: "+ book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book storedBook = getBook(id).get();
        storedBook.setName(book.getName());
        storedBook.setPublishYear(book.getPublishYear());
        storedBook.setAuthor(book.getAuthor());
        bookRepository.save(storedBook);
        log.info("Book updated: "+ book);
        return storedBook;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
        log.info("Book deleted: book_id"+ id);
    }

    @Override
    public boolean isBookExist(Book book) {
        Book storedBook = bookRepository.findFirstByNameAndAuthorId(book.getName(), book.getAuthor().getId());
        return storedBook != null;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public List<BookAuthor> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Optional<BookAuthor> getAuthor(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public boolean isAuthorExist(BookAuthor author) {
        Optional<BookAuthor> storedAuthor = getAuthor(author.getId());
        return storedAuthor.isPresent();
    }

    @Override
    public void saveUpdateAuthor(BookAuthor author) {
        authorRepository.save(author);
        if(isAuthorExist(author)){
            log.info("Book author updated: "+ author);
        } else{
            log.info("Book author inserted: "+ author);
        }
    }
}
