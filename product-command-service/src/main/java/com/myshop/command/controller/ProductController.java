package com.myshop.command.controller;

import com.myshop.command.entity.Product;
import com.myshop.command.service.ProductService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products/command")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    public Product saveProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }
}
