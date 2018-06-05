package com.ay.testlab.atom.book;

import javax.persistence.*;

@Entity
@Table
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_SEQ")
    @SequenceGenerator(sequenceName = "book_seq", allocationSize = 1, name = "BOOK_SEQ")
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column
    private Long publishYear;

    @ManyToOne
    @JoinColumn(name = "id")
    private BookAuthor author;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BookAuthor getAuthor() {
        return author;
    }

    public void setAuthor(BookAuthor author) {
        this.author = author;
    }

    public Long getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Long publishYear) {
        this.publishYear = publishYear;
    }
}
