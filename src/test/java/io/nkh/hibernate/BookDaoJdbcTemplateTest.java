package io.nkh.hibernate;

import io.nkh.hibernate.dao.BookDao;
import io.nkh.hibernate.dao.BookDao2;
import io.nkh.hibernate.dao.BookDaoJDBCTemplate;
import io.nkh.hibernate.domain.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"io.nkh.hibernate.dao"})
@DataJpaTest
public class BookDaoJdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    BookDao2 bookDao;

    @BeforeEach
    void setUp() {
        bookDao = new BookDaoJDBCTemplate(jdbcTemplate);
    }

    @Test
    void testFindAllBooks() {
        List<Book> books = bookDao.findAllBooks();
        assertThat(books).isNotNull();
        assertThat(books.size()).isGreaterThan(1);
    }
}
