package com.example.paymanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.paymanagement.model.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

}