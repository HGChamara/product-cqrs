package com.myshop.query.service;

import com.myshop.dto.ProductEvent;
import com.myshop.query.entity.Product;
import com.myshop.query.repository.ProductRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductQueryService {
    private final ProductRepository productRepository;

    public ProductQueryService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @KafkaListener(topics = "product-event-topic", groupId = "group2")
    public void processProductsEvent(ProductEvent event) {
        if(event.getEventType().equals("CreateProduct")) {
            productRepository.save(event.getProduct());
        } else if(event.getEventType().equals("UpdateProduct")) {
            Product updatedProduct = event.getProduct();
            Product product = productRepository.findById(updatedProduct.getId()).get();
            product.setAmount(updatedProduct.getAmount());
            product.setName(updatedProduct.getName());
            product.setDescription(updatedProduct.getDescription());
            productRepository.save(product);
        }
    }
}
