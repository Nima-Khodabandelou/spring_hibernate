package io.nkh.hibernate.dao;


import io.nkh.hibernate.domain.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by jt on 8/22/21.
 */
public interface AuthorDao {
    Author getById(Long id);

    Author findAuthorByName(String firstName, String lastName);

    List<Author> findAuthorsByLastName(String lastName, Pageable pageable);

    Author saveNewAuthor(Author author);

    Author updateAuthor(Author author);

    void deleteAuthorById(Long id);

    List<Author> listOfAuthorByLastNameSuchAs(String lastName);

    List<Author> findAll();

    Author findAuthorByNameCriteria(String firstName, String lastName);

    Author FindAuthorByNameNative(String firstName, String lastName);
}
