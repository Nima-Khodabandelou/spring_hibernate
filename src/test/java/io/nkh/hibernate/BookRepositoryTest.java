package io.nkh.hibernate;

import io.nkh.hibernate.domain.Book;
import io.nkh.hibernate.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackages = {"io.nkh.hibernate.dao"})
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void testFindBookByTitleWithQuery() {
        Book book = bookRepository.findBookByTitleWithQuery("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void testFindBookByTitleWithQueryNamed() {
        Book book = bookRepository.findBookByTitleWithQueryNamed("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void testFindBookByTitleWithNativeQuery() {
        Book book = bookRepository.findBookByTitleWithNativeQuery("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void testJpaNamed() {
        Book book = bookRepository.jpaNamed("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void testBookStream() {
        AtomicInteger count = new AtomicInteger();
        bookRepository.findAllByTitleNotNull().forEach(book -> {
            count
                    .incrementAndGet();
        });
        assertThat(count.get()).isGreaterThan(1);
    }

    @Test
    void testEmptyResultException() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
           Book book = bookRepository.readByTitle("foobar4");
        });
    }

    @Test
    void testNullParam() {
        assertNull(bookRepository.getByTitle(null));
    }

    @Test
    void testNoException() {
        assertNull(bookRepository.getByTitle("foo"));
    }

    @Test
    void testBookFuture() throws ExecutionException, InterruptedException {
        Future<Book> bookFuture = bookRepository.queryByTitle("Clean Code");

        Book book = bookFuture.get();

        assertNotNull(book);
    }
}
