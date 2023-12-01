package io.nkh.hibernate.dao;

import io.nkh.hibernate.domain.Book;
import io.nkh.hibernate.repository.BookRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class BookDaoSdjpa implements BookDao{

    private final BookRepository bookRepository;

    public BookDaoSdjpa(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> findAllBooksSortByTitle(Pageable pageable) {

        Page<Book> bookPage = bookRepository.findAll(pageable);

        return bookPage.getContent();
    }

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findAllBooks(int pageSize, int offset) {

        Pageable pageable = PageRequest.ofSize(pageSize);

        if(offset > 0) {
            pageable = pageable.withPage(offset / pageSize);
        } else {
            pageable = pageable.withPage(0);
        }
        return this.findAllBooks(pageable);
    }

    @Override
    public List<Book> findAllBooks(Pageable pageable) {
        return bookRepository.findAll(pageable).getContent();
    }

    @Override
    public Book findByIsbn(String isbn) {
        return null;
    }

    @Override
    public Book getById(Long id) {
        return null;
    }

    @Override
    public Book findBookByTitle(String title) {
        return null;
    }

    @Override
    public Book saveNewBook(Book book) {
        return null;
    }

    @Override
    public Book updateBook(Book book) {
        return null;
    }

    @Override
    public void deleteBookById(Long id) {

    }
}
