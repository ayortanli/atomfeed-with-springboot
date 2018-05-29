package com.ay.testlab.atom.author;

import java.util.List;

public interface AuthorService {

    void insertAuthor(Author author);
    boolean isAuthorExist(Author author);
    List<Author> getAllAuthors();
}
