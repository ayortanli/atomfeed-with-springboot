package com.ay.testlab.atom.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void insertBook(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book book) {
        Book storedBook = getBook(id).get();
        storedBook.setName(book.getName());
        storedBook.setPublishYear(book.getPublishYear());
        storedBook.setAuthor(book.getAuthor());
        bookRepository.save(storedBook);
        return storedBook;
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public boolean isBookExist(Book book) {
        Book storedBook = bookRepository.findFirstByNameAndAuthorId(book.getName(), book.getAuthor().getId());
        return storedBook !=null;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }
}