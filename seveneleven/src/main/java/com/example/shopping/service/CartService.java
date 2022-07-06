package com.example.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping.model.Cart;
import com.example.shopping.model.MyUser;
import com.example.shopping.model.Order;
import com.example.shopping.model.Product;
import com.example.shopping.repository.CartRepository;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired 
    private OrderService orderService;

    public void saveCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void deleteCartById(Long id) {
        cartRepository.deleteById(id);
    }

    public void updateCart(Cart cart) {
        cartRepository.save(cart);
    }

    public Cart getCartById(Long id) {
        return cartRepository.findById(id).get();
    }

    public List<Cart> getAllCarts() {
        return (List<Cart>) cartRepository.findAll();
    }

    public void addProduct(MyUser myUser, Product product, int quantity) {
        cartRepository.save(new Cart(myUser, product, quantity));
    }

    public List<Cart> getAllCartsByUser(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }

    public void deleteAllCartsByUserId(Long userId) {
        cartRepository.deleteAllByUserId(userId);
    }

    public List<Order> exportOrders(List<Cart> carts) {
        MyUser myUser = carts.get(0).getMyUser();
        for (var cart : carts) {
            orderService.saveOrder(new Order(cart.getMyUser(), cart.getProduct(), cart.getQuantity()));
        }
        return orderService.findOrdersByMyUserId(myUser.getUserId());
    }

}
