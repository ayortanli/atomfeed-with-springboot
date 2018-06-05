package com.ay.testlab.atom.book;

import javax.persistence.*;

public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOOK_AUTHOR_SEQ")
    @SequenceGenerator(sequenceName = "book_author_seq", allocationSize = 1, name = "BOOK_AUTHOR_SEQ")
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String surname;

    @Column(length = 200, nullable = false)
    private String country;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
