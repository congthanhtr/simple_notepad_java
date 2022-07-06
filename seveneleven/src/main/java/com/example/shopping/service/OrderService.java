package com.example.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.model.Order;
import com.example.shopping.repository.OrderRepository;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();
    }

    public Order getOrderById(long id) {
        return orderRepository.findById(id).get();
    }

    public void updateOrder(Order order) {
        orderRepository.save(order);
    }

    public void deleteOrderById(long id) {
        orderRepository.deleteById(id);
    }    

    public List<Order> findOrdersByMyUserId(long userId) {
        return orderRepository.findOrdersByMyUserId(userId);
    }

    
}
