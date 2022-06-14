package com.example.paymanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.paymanagement.model.Chapter;

@Repository
public interface ChapterRepository extends CrudRepository<Chapter, Long> {

}
