package com.example.seveneleven;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.shopping.model.Cart;
import com.example.shopping.model.MyUser;
import com.example.shopping.model.Product;
import com.example.shopping.repository.CartRepository;
import com.example.shopping.service.CartService;
import com.example.shopping.service.MyUserService;
import com.example.shopping.service.ProductService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestCart {
    @Autowired
    private CartService cartService;

    @Autowired
    private MyUserService userService;

    @Autowired
    private ProductService productService;

    @Test
    public void addCart() {

        MyUser myUser = userService.getUserById(1L);

        Product product = productService.getProductById(1L);

        Cart cart = new Cart(myUser, product, 2);
        cartService.addProduct(myUser, product, 1);
        cartService.saveCart(cart);
    }
    
}
