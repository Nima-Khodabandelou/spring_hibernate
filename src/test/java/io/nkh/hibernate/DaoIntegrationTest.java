package io.nkh.hibernate;

import io.nkh.hibernate.dao.AuthorDao;
import io.nkh.hibernate.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"io.nkh.hibernate.dao"})
@ActiveProfiles("dev")
public class DaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Test
    void testGetAuthorById() {
        Author found = authorDao.getById(1L);
        assertThat(found).isNotNull();
    }

    @Test
    void testGetAuthorByName() {
        Author author = authorDao.findAuthorByName("author1 fName", "author1 lName");
        assertThat(author).isNotNull();
    }

    @Test
    void testSaveNewAuthor() {
        Author author = new Author();
        author.setFirstName("author2f");
        author.setLastName("author2l");
        Author saved = authorDao.saveNewAuthor(author);
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("author4f");
        author.setLastName("author4l");
        Author saved = authorDao.saveNewAuthor(author);
        saved.setLastName("author44l");
        Author updated = authorDao.updateAuthor(saved);
        assertThat(updated.getLastName()).isEqualTo("author44l");
    }
}
