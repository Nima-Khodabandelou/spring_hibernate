package io.nkh.hibernate;


import io.nkh.hibernate.dao.AuthorDao;
import io.nkh.hibernate.domain.Author;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ComponentScan(basePackages = {"io.nkh.hibernate.dao"})
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SdjDaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void getById() {
        var found = authorDao.getById(8L);
        assertThat(found).isNotNull();
    }

    @Test
    void testSaveNewAuthor() {
        Author author = new Author();
        author.setFirstName("new author f");
        author.setLastName("new author l");
        var saved = authorDao.saveNewAuthor(author);
        assertThat(saved.getId()).isGreaterThan(0);
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("new author f1");
        author.setLastName("new author l1");
        var saved = authorDao.saveNewAuthor(author);
        Author retrieved = authorDao.getById(saved.getId());
        retrieved.setLastName("new author l11");
        assertThat(retrieved.getFirstName()).isEqualTo("new author f1");
    }

    @Test
    void testDeleteAuthorById() {
        Author author = new Author();
        author.setFirstName("new author f12");
        author.setLastName("new author l12");

        var saved = authorDao.saveNewAuthor(author);

        authorDao.deleteAuthorById(saved.getId());

        assertThrows(JpaObjectRetrievalFailureException.class, () -> {
            Author deleted = authorDao.getById(saved.getId());
        });
    }

    @Test
    void testFindAuthorByName() {
        Author author = new Author();
        author.setFirstName("new author f13");
        author.setLastName("new author l13");

        Author saved = authorDao.saveNewAuthor(author);

        Author found = authorDao.findAuthorByName(saved.getFirstName(), saved.getLastName());
        assertThat(found).isNotNull();
    }

    @Test
    void testGetAuthorByNameNotFound() {
        assertThrows(EntityNotFoundException.class, () -> {
           Author author = authorDao.findAuthorByName("auth", "nam");
        });
    }
}
