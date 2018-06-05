package com.ay.testlab.atom.book;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findFirstByNameAndAuthorId(String name, Long authorId);
}
