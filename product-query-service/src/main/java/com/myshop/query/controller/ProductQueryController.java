package com.myshop.query.controller;

import com.myshop.query.entity.Product;
import com.myshop.query.service.ProductQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/query/product")
public class ProductQueryController {

    private final ProductQueryService productQueryService;

    public ProductQueryController(ProductQueryService productQueryService){
        this.productQueryService = productQueryService;
    }

    @GetMapping()
    public List<Product> getAllProducts(){
        return productQueryService.getAllProducts();
    }
}
