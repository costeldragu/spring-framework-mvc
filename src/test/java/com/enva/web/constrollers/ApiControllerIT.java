package com.enva.web.constrollers;

import com.enva.model.Product;
import com.enva.service.ProductService;
import com.enva.web.controllers.ApiController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ApiControllerIT {

    private static final String API_PREFIX = "/api/v1";

    @Mock
    private ProductService productService;

    private ApiController apiController;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        apiController = new ApiController(productService);
        mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        String expected = "[{\"id\":1,\"category\":\"Category 1\",\"name\":\"Product 1\",\"description\":\"A TV LG description\",\"price\":10.0}]";
        List<Product> products = Collections.singletonList(new Product.Builder().setId(1).setName("Product 1").setCategory("Category 1").setDescription("A TV LG description").setPrice(10.0).build());

        Mockito.when(productService.getAllProducts()).thenReturn(products);

        mockMvc.perform(
                MockMvcRequestBuilders.get(API_PREFIX + "/products/all")
        ).andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        String updateProduct = "{\"id\":1,\"category\":\"Category 1\",\"name\":\"Product 1\",\"description\":\"A TV LG description\",\"price\":10.0}";
        Product product = new Product.Builder().setId(1).setName("Product 1")
                .setCategory("Category 1")
                .setDescription("A TV LG description")
                .setPrice(10.0).build();

        Mockito.when(productService.updateProduct(Mockito.eq(product))).thenReturn(product);
        mockMvc.perform(
                MockMvcRequestBuilders.post(API_PREFIX + "/product/update")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(updateProduct)
        ).andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }
}
