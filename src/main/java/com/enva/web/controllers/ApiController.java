package com.enva.web.controllers;

import com.enva.model.Product;
import com.enva.repository.ProductRepository;
import com.enva.service.ProductService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiController extends BaseController {

    private final ProductService productService;

    public ApiController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path = "products/all", method = RequestMethod.GET)
    List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @RequestMapping(path = "/product/{id}/delete", method = RequestMethod.GET)
    public Boolean showProductConfirmDeletePage(@PathVariable(name = "id") Long id) {
        return productService.deleteProduct(productService.getProduct(id));
    }

    @RequestMapping(path = "/product/update", method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @RequestMapping(path = "/product/{id}", method = RequestMethod.GET)
    public Product getProduct(@PathVariable(name = "id") Long id) {
        return productService.getProduct(id);
    }
}
