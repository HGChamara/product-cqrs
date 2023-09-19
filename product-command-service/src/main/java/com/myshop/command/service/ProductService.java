package com.myshop.command.service;

import com.myshop.dto.ProductEvent;
import com.myshop.command.entity.Product;
import com.myshop.command.repository.ProductRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private KafkaTemplate<String, Object> kafkaTemplate;
    public ProductService(ProductRepository productRepository, KafkaTemplate kafkaTemplate) {
        this.productRepository = productRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    public Product createProduct(Product product) {
        Product productDO = productRepository.save(product);
        ProductEvent event = new ProductEvent("CreateProduct", product);
        kafkaTemplate.send("product-event-topic", event);
        return productDO;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id).get();
        product.setAmount(updatedProduct.getAmount());
        product.setName(updatedProduct.getName() != null ? updatedProduct.getName() : product.getName());
        product.setDescription(updatedProduct.getDescription() != null ? updatedProduct.getDescription() : product.getDescription());

        ProductEvent event = new ProductEvent("UpdateProduct", product);
        kafkaTemplate.send("product-event-topic", event);
        return productRepository.save(product);
    }

}
