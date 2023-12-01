package io.nkh.hibernate.service;

import io.nkh.hibernate.domain.Product;
import io.nkh.hibernate.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveAndFlush(product);
    }

    @Override
    public Product updateQAH(Long id, Integer quantityAtHand) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setQuantityAtHand(quantityAtHand);

        return productRepository.saveAndFlush(product);
    }
}
