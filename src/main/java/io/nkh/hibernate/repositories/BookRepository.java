package io.nkh.hibernate.repositories;


import io.nkh.hibernate.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
