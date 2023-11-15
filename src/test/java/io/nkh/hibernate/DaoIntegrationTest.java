package io.nkh.hibernate;

import io.nkh.hibernate.dao.AuthorDao;
import io.nkh.hibernate.dao.BookDao;
import io.nkh.hibernate.domain.Author;
import io.nkh.hibernate.domain.Book;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"io.nkh.hibernate.dao"})
@ActiveProfiles("dev")
public class DaoIntegrationTest {

    @Autowired
    AuthorDao authorDao;

    @Autowired
    BookDao bookDao;

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

    @Test
    void testDeleteAuthorById() {
        Author author = new Author();
        author.setFirstName("author6f");
        author.setLastName("author6l");
        Author saved = authorDao.saveNewAuthor(author);
        authorDao.deleteAuthorById(saved.getId());

        Author deleted = authorDao.getById(saved.getId());
        assertThat(deleted).isNull();

        assertThat(authorDao.getById(saved.getId()));
    }

    @Test
    void testListOfAuthorByNameSuchAs() {
        List<Author> authors = authorDao.listOfAuthorByLastNameSuchAs("author6l");
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    void TestFindBookByIsbn() {
        Book book = new Book();
        book.setIsbn("8379" + RandomString.make());
        book.setTitle("some book");

        Book saved = bookDao.saveNewBook(book);

        Book fetched = bookDao.findByIsbn(book.getIsbn());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testFindAllAuthors() {
        List<Author> authors = authorDao.findAll();

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    void testFindAuthorByNameCriteria() {
        Author found = authorDao.findAuthorByNameCriteria("author1 fName", "author1 lName");
        assertThat(found).isNotNull();
    }

    @Test
    void testFindAuthorByNameNative() {
        Author found = authorDao.FindAuthorByNameNative("author1 fName", "author1 lName");
        assertThat(found).isNotNull();
    }
}
