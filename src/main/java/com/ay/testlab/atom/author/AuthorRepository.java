package com.ay.testlab.atom.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {

    Author findFirstByNameAndSurname(String name, String surname);

    @Query("Select max(o.lastUpdateTime) from Author o")
    Date lastUpdateTime();

    List<Author> findByLastUpdateTimeAfter(Date after);

}
