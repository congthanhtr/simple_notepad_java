package com.example.seveneleven.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.seveneleven.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product,Long> {
    
}
