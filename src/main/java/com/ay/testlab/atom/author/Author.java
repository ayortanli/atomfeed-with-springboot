package com.ay.testlab.atom.author;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AUTHOR_SEQ")
    @SequenceGenerator(sequenceName = "author_seq", allocationSize = 1, name = "AUTHOR_SEQ")
    private Long id;

    @Column(length = 200, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String surname;

    @Column(length = 200, nullable = false)
    private String country;

    @JsonIgnore
    @Column
    private Date lastUpdateTime;

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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
