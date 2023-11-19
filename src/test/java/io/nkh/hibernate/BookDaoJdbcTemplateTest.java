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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Test
    void testFindAllBooksPage1_SortByTitle() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(0, 10,
                Sort.by(Sort.Order.desc("title"))));
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testFindAllBooksPage1_pageable() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(0, 10));
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testFindAllBooksPage2_pageable() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(2, 3));
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(3);
    }

    @Test
    void testFindAllBooksPage10_pageable() {
        List<Book> books = bookDao.findAllBooks(PageRequest.of(7, 2));
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }

    @Test
    void testFindAllBooksPage1() {
        List<Book> books = bookDao.findAllBooks(10, 0);
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(10);
    }

    @Test
    void testFindAllBooksPage2() {
        List<Book> books = bookDao.findAllBooks(5, 3);
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(5);
    }

    @Test
    void testFindAllBooksPage10() {
        List<Book> books = bookDao.findAllBooks(10, 100);
        assertThat(books).isNotNull();
        assertThat(books.size()).isEqualTo(0);
    }
}
