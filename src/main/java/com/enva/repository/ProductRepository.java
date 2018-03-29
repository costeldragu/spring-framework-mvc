package com.enva.repository;

import com.enva.model.Product;

import java.util.List;

public interface ProductRepository {

    Product saveProduct(Product product);

    boolean saveProducts(List<Product> products);

    Product getProduct(String productId);

    boolean deleteProduct(Product product);

}
