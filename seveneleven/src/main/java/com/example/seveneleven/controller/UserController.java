package com.example.seveneleven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.seveneleven.service.CartService;
import com.example.seveneleven.service.MyUserService;
import com.example.seveneleven.service.OrderService;
import com.example.seveneleven.service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MyUserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/{user_id}")
    public String getUserHome(Model model, @PathVariable("user_id") long userId) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("products", productService.getAllProducts());

        return "user/userHome";
    }

    @GetMapping("/{user_id}/cart/")
    public String getUserCart(@PathVariable("user_id") long userId, Model model) {
        model.addAttribute("carts", cartService.getAllCartsByUser(userId));
        model.addAttribute("user", userService.getUserById(userId));

        if (cartService.getAllCartsByUser(userId).size() == 0) {
            model.addAttribute("empty", true);
        }

        return "user/userCart";
    }

    @PostMapping("/{user_id}/cart/add/{product_id}")
    public String addToCart(@PathVariable("user_id") long userId, 
                            @PathVariable("product_id") long productId,
                            @RequestParam("quantity") int quantity) {
        cartService.addProduct(userService.getUserById(userId), 
                               productService.getProductById(productId), quantity);

        return "redirect:/user/" + userId + "/cart/";
    }

    @GetMapping("/{user_id}/product/{product_id}")
    public String getProduct(@PathVariable("user_id") long userId, 
                             @PathVariable("product_id") long productId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("product", productService.getProductById(productId));

        return "user/viewProduct";
    }

    @GetMapping("/{userid}/cart/purchase")
    public String purchaseCart(@PathVariable("userid") long userId) {
        cartService.exportOrders(cartService.getAllCartsByUser(userId));
        cartService.deleteAllCartsByUserId(userId);

        return "redirect:/user/" + userId + "/order";
    }

    @GetMapping("/{user_id}/cart/delete/{cart_id}")
    public String deleteProductFromCart(@PathVariable("user_id") long userId, @PathVariable("cart_id") long cartId) {
        cartService.deleteCartById(cartId);

        return "redirect:/user/" + userId + "/cart/";
    }

    @GetMapping("/{userId}/cart/deleteall")
    public String deleteAllCart(@PathVariable("userId") long userId) {
        cartService.deleteAllCartsByUserId(userId);

        return "redirect:/user/" + userId + "/cart/";
    }

    @GetMapping("/{userId}/order")
    public String getAllOrders(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        model.addAttribute("orders", orderService.findOrdersByMyUserId(userId));
        if (cartService.getAllCartsByUser(userId).size() == 0) {
            model.addAttribute("empty", true);
        }
        return "user/viewOrders";
    }
    @GetMapping("/{userId}/order/{orderId}/delete")
    public String deleteOrder(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) {
        orderService.deleteOrderById(orderId);
        return "redirect:/user/" + userId + "/order";
    }
}
