package com.example.shopping.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.shopping.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    
}
