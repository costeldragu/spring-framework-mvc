package com.enva.service.impl;

import com.enva.model.Product;
import com.enva.repository.ProductRepository;
import com.enva.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.saveProduct(product);
    }

    @Override
    public Product updateProduct(Product product) {
        if (product.getId() != null) {
            return productRepository.updateProduct(product);
        } else {
            return saveProduct(product);
        }
    }

    @Override
    public boolean saveProducts(List<Product> products) {
        productRepository.saveProducts(products);
        return true;
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.getProduct(productId);
    }

    @Override
    public boolean deleteProduct(Product product) {
        return productRepository.deleteProduct(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

}
