package com.enva.service.impl;

import com.enva.model.Product;
import com.enva.repository.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;
    ProductServiceImpl productService;

    @Before
    public void setUp() {
        productService = Mockito.spy(new ProductServiceImpl(productRepository));
    }

    @Test
    public void testGetProduct() {
        Product expected = getExpectedProducts().get(2);
        Mockito.when(productRepository.getProduct(Mockito.eq(1L))).thenReturn(getExpectedProducts().get(1));

        Product actual = productService.getProduct(1L);

        Assert.assertEquals(expected, actual);

    }

    private Map<Integer, Product> getExpectedProducts() {
        Map<Integer, Product> products = new HashMap<>();
        products.put(1, new Product.Builder().setId(1).setName("Product 1").setCategory("Category 1").setDescription("Description").setPrice(10.0).build());
        products.put(2, new Product.Builder().setId(2).setName("Product 2").setCategory("Category 2").setDescription("Description").setPrice(11.0).build());
        products.put(3, new Product.Builder().setId(3).setName("Product 3").setCategory("Category 3").setDescription("Description").setPrice(13.0).build());
        return products;
    }
}
