package com.enva.repository.impl;

import com.enva.TestConfig;
import com.enva.model.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@Rollback(value = true)
public class ProductRepositoryImplIT {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private  ProductRepositoryImpl productRepository;

    @Before
    public void setUp() {
        productRepository = new ProductRepositoryImpl(jdbcTemplate);
    }

    @Test
    public void testSaveLoadProduct() {
        Map<Integer, Product> productMap = getExpectedProducts();
        productRepository.saveProduct(productMap.get(1));
        Product actual = productRepository.getProduct(1L);

        Assert.assertEquals(productMap.get(1), actual);
    }

    @Test
    public void testUpdateProduct() {
        Map<Integer, Product> productMap = getExpectedProducts();
        Product updateProduct = productMap.get(1);
        productRepository.saveProduct(updateProduct);
        updateProduct.setCategory("Updated category");
        productRepository.updateProduct(updateProduct);
        Product actual = productRepository.getProduct(1L);
        Assert.assertEquals(updateProduct, actual);
    }


    private Map<Integer, Product> getExpectedProducts() {
        Map<Integer, Product> products = new HashMap<>();
        products.put(1, new Product.Builder().setId(1).setName("Product 1").setCategory("Category 1").setDescription("Description").setPrice(10.0).build());
        products.put(2, new Product.Builder().setId(2).setName("Product 2").setCategory("Category 2").setDescription("Description").setPrice(11.0).build());
        products.put(3, new Product.Builder().setId(3).setName("Product 3").setCategory("Category 3").setDescription("Description").setPrice(13.0).build());
        return products;
    }
}
