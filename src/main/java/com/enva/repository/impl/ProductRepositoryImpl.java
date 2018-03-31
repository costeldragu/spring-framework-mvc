package com.enva.repository.impl;

import com.enva.model.Product;
import com.enva.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    private  static Long lastId = 1L;
    private final Map<Long, Product> productsTable = new HashMap<>();

    @Override
    public Product saveProduct(Product product) {
        if (product.getId() == null) {
            product.setId(getNextId());
        }
        productsTable.put(product.getId(), product);
        return null;
    }

    @Override
    public boolean saveProducts(List<Product> products) {
        products.forEach(this::saveProduct);
        return true;
    }

    @Override
    public Product getProduct(Long productId) {
        return productsTable.get(productId);
    }

    @Override
    public boolean deleteProduct(Product product) {
        Product exitingProduct = getProduct(product.getId());
        if (product.equals(exitingProduct)) {
            productsTable.remove(product.getId());
            return true;
        }
        return false;
    }
    @Override
    public  List<Product> getAllProducts() {
      return new ArrayList<>(productsTable.values());
    }

   private synchronized Long getNextId() {
        return lastId++;
   }
}
