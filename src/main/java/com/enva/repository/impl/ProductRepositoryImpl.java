package com.enva.repository.impl;

import com.enva.model.Product;
import com.enva.repository.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ProductRepositoryImpl implements ProductRepository {
    private final Map<String, Product> productsTable = new HashMap<>();

    @Override
    public Product saveProduct(Product product) {
        if (product.getUUID() == null) {
            product.setUUID(UUID.randomUUID().toString());
        }
        productsTable.put(product.getUUID(), product);
        return null;
    }

    @Override
    public boolean saveProducts(List<Product> products) {
        products.forEach(this::saveProduct);
        return true;
    }

    @Override
    public Product getProduct(String productId) {
        return productsTable.get(productId);
    }

    @Override
    public boolean deleteProduct(Product product) {
        Product exitingProduct = getProduct(product.getUUID());
        if (product.equals(exitingProduct)) {
            productsTable.remove(product.getUUID());
            return true;
        }
        return false;
    }
}
