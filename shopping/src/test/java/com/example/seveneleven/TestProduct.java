package com.example.seveneleven;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.shopping.model.Product;
import com.example.shopping.repository.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestProduct {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void addProduct() {
        Product product = new Product(1L, "Product 3", "Description 3", 30000);
        productRepository.save(product);
    }
    
}
