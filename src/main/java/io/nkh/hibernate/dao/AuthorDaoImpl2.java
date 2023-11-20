package io.nkh.hibernate.dao;

import io.nkh.hibernate.domain.Author;
import io.nkh.hibernate.repositories.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Primary
@Component
public class AuthorDaoImpl2 implements AuthorDao {

    private final AuthorRepository authorRepository;

    public AuthorDaoImpl2(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author getById(Long id) {
        return authorRepository.getById(id);
    }

    @Override
    public Author findAuthorByName(String firstName, String lastName) {
        return authorRepository.findAuthorByFirstNameAndLastName(firstName, lastName)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Author> findAuthorsByLastName(String lastName, Pageable pageable) {
        return authorRepository.findAuthorByLastName(lastName, pageable).getContent();
    }

    @Override
    public Author saveNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    // By default, Spring Data JPA does its own transactional context. Bellow lets it acts in a transaction
    @Transactional
    @Override
    public Author updateAuthor(Author author) {
         var found = authorRepository.getById(author.getId());
         found.setFirstName(author.getFirstName());
         found.setFirstName(author.getLastName());
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> listOfAuthorByLastNameSuchAs(String lastName) {
        return null;
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public Author findAuthorByNameCriteria(String firstName, String lastName) {
        return null;
    }

    @Override
    public Author FindAuthorByNameNative(String firstName, String lastName) {
        return null;
    }
}
