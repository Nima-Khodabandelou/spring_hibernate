package io.nkh.hibernate.repository;

import io.nkh.hibernate.domain.Product;
import io.nkh.hibernate.domain.ProductStatus;
import io.nkh.hibernate.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("dev")
@ComponentScan(basePackages = {"io.nkh.hibernate.service", "io.nkh.hibernate.repository"})
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductService productService;

    @Test
    void findProductByDescription() {
        Product product = productRepository.findProductByDescription("PRODUCT1");

        assertNotNull(product);
        assertNotNull(product.getCategories());
    }

    @Test
    void findByDescription() {
        Product product = productRepository.findByDescription("PRODUCT1").get();

        assertNotNull(product);
        assertNotNull(product.getCategories());
    }

    @Test
    void addAndUpdateProduct() {
        Product product = new Product();
        product.setDescription("My Product");
        product.setProductStatus(ProductStatus.NEW);

        Product savedProduct = productService.saveProduct(product);

        // savedProduct.setQuantityAtHand(25);

        Product savedProduct2 = productService.updateQAH(savedProduct.getId(), 25);

        System.out.println(savedProduct2.getQuantityAtHand());

    }
}