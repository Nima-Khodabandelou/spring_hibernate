package io.nkh.hibernate.dao;

import io.nkh.hibernate.domain.Book;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookDao2 {

    List<Book> findAllBooksSortByTitle(Pageable pageable);

    List<Book> findAllBooks(Pageable pageable);

    List<Book> findAllBooks(int pageSize, int offset);

    List<Book> findAllBooks();

    /*Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

    Book findByIsbn(String isbn);*/
}
