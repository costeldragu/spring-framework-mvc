package com.enva.web.controllers;

import com.enva.model.Product;
import com.enva.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController extends BaseController {

    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String showHomePage(@RequestParam(name = "sort", defaultValue = "ASC") String sortBy) {
        return "index";
    }

    @RequestMapping(path = "/product/{id}/delete", method = RequestMethod.GET)
    public String showProductConfirmDeletePage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("productId", id);
        return "delete";
    }

    @RequestMapping(path = "/product/{id}/edit", method = RequestMethod.GET)
    public String showProductEditPage(@PathVariable("id") Long id, Model model) {
        model.addAttribute("info","Edit product");
        model.addAttribute("product", productService.getProduct(id));
        return "update";
    }

    @RequestMapping(path = "/product/add", method = RequestMethod.GET)
    public String showProductAddPage(Model model) {
        model.addAttribute("info","Add product");
        model.addAttribute("product", new Product());
        return "update";
    }

}
