package com.enva.service;

import com.enva.model.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    boolean saveProducts(List<Product> products);

    Product getProduct(Long productId);

    boolean deleteProduct(Product product);

    List<Product> getAllProducts();
}
