package com.ay.testlab.atom.book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void insertBook(Book book);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
    boolean isBookExist(Book author);
    List<Book> getAllBooks();
    Optional<Book> getBook(Long id);
}
