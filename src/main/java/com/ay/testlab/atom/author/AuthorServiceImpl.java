package com.ay.testlab.atom.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public void insertAuthor(Author author) {
        authorRepository.save(author);
    }

    @Override
    public boolean isAuthorExist(Author author) {
        Author storedAuthor = authorRepository.findFirstByNameAndSurname(author.getName(), author.getSurname());
        return storedAuthor!=null;
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
}
