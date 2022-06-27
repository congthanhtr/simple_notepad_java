package com.example.seveneleven.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.seveneleven.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long> {
    @Query("SELECT o FROM Order o WHERE user_id = :userId")
    List<Order> findOrdersByMyUserId(@Param("userId") long userId);
}
