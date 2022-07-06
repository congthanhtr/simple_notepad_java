package com.example.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.shopping.model.Cart;

@Repository
public interface CartRepository extends CrudRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE user_id = ?#{[0]}")
    List<Cart> findAllByUserId(@Param("userId") long userId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Cart c WHERE user_id =:userId")
    void deleteAllByUserId(@Param("userId") long userId);
}
