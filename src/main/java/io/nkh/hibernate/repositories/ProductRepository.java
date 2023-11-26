package io.nkh.hibernate.repositories;

import io.nkh.hibernate.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findProductByDescription(String description);
}
