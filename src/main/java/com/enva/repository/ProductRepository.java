package com.enva.repository;

import com.enva.model.Product;

import java.util.List;

public interface ProductRepository {

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    List<Product> saveProducts(List<Product> products);

    Product getProduct(Long productId);

    boolean deleteProduct(Product product);

    List<Product> getAllProducts();

}
