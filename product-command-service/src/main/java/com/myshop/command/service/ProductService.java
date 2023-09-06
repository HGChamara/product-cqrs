package com.myshop.command.service;

import com.myshop.command.entity.Product;
import com.myshop.command.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id).get();

        product.setAmount(updatedProduct.getAmount());
        product.setName(updatedProduct.getName() != null ? updatedProduct.getName() : product.getName());
        product.setDescription(updatedProduct.getDescription() != null ? updatedProduct.getDescription() : product.getDescription());
        return productRepository.save(product);
    }

}
