package com.example.paymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.paymanagement.model.MoneyIn;

@Repository
public interface MoneyInRepository extends CrudRepository<MoneyIn, Long> {
    @Query("SELECT m FROM MoneyIn m WHERE chapter_id = ?#{[0]}")
    List<MoneyIn> getAllMoneyInByChapterId(@Param("chapter_id") long chapter_id);
}