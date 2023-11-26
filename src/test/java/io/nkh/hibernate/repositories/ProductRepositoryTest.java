package io.nkh.hibernate.repositories;

import io.nkh.hibernate.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("dev")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void findProductByDescription() {
        Product product = productRepository.findProductByDescription("PRODUCT1");

        assertNotNull(product);
        assertNotNull(product.getCategories());
    }
}