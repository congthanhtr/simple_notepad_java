package com.example.paymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.paymanagement.model.MoneyOut;

@Repository
public interface MoneyOutRepository extends CrudRepository<MoneyOut, Long> {
    @Query("SELECT m FROM MoneyOut m WHERE chapter_id = ?#{[0]}")
    List<MoneyOut> getAllMoneyOutByChapterId(@Param("chapter_id") long chapter_id);
}
