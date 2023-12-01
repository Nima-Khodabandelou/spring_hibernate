package io.nkh.hibernate.service;

import io.nkh.hibernate.domain.Product;

public interface ProductService {

    Product saveProduct(Product product);

    Product updateQAH(Long id, Integer quantityAtHand);
}
