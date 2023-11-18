package io.nkh.hibernate.dao;

import io.nkh.hibernate.domain.Book;

import java.util.List;

public interface BookDao2 {

    List<Book> findAllBooks();

    /*Book getById(Long id);

    Book findBookByTitle(String title);

    Book saveNewBook(Book book);

    Book updateBook(Book book);

    void deleteBookById(Long id);

    Book findByIsbn(String isbn);*/
}
