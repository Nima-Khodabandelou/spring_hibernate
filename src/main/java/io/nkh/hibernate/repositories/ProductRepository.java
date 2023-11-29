package io.nkh.hibernate.repositories;

import io.nkh.hibernate.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByDescription(String description);

    Optional<Product> findByDescription(String description);
}
