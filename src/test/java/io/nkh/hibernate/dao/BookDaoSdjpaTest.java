package io.nkh.hibernate.dao;

import io.nkh.hibernate.domain.Author;
import io.nkh.hibernate.domain.Book;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"io.nkh.hibernate.dao"})
@ActiveProfiles("dev")
class BookDaoSdjpaTest {

    @Autowired
    BookDao bookDao;

    @Test
    void testFindAllBooks() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(0, 4));

        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(4);
    }
}