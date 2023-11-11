package io.nkh.hibernate.repositories;


import io.nkh.hibernate.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
