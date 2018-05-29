package com.ay.testlab.atom.author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    void insertAuthor(Author author);
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
    boolean isAuthorExist(Author author);
    List<Author> getAllAuthors();
    Optional<Author> getAuthor(Long id);
}
