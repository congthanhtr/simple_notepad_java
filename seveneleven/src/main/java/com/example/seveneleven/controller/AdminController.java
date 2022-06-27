package com.example.seveneleven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.seveneleven.model.Product;
import com.example.seveneleven.service.MyUserService;
import com.example.seveneleven.service.OrderService;
import com.example.seveneleven.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ProductService productService;

    @Autowired
    private MyUserService userService;


    @Autowired
    private OrderService orderService;

    @GetMapping("/{userId}/product")
    public String getAllProducts(@PathVariable("userId") Long userId ,Model model) {
        model.addAttribute("admin", userService.getUserById(userId));
        model.addAttribute("products", productService.getAllProducts());
        return "admin/viewProducts";
    }
    
    @GetMapping("/{userId}/product/add")
    public String addProduct(@PathVariable("userId") Long userId, Model model) {
        if (userService.getUserById(userId).getRole() == "USER") {
            return "redirect:/user/" + userId;
        }
        else {
            model.addAttribute("admin", userService.getUserById(userId));
            model.addAttribute("product", new Product());
            return "admin/addProduct";
        } 
    }

    @PostMapping("/{userId}/product/add")
    public String addProduct(@PathVariable("userId") Long userId, Product product) {
        productService.saveProduct(product);
        return "redirect:/admin/" + userId + "/product/";
    }

    @GetMapping("/{userId}/product/{productId}/delete")
    public String deleteProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId) {
        productService.deleteProductById(productId);
        return "redirect:/admin/" + userId + "/product/";
    }

    @GetMapping("/{userId}/product/{productId}/edit")
    public String editProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId, Model model) {
        if (userService.getUserById(userId).getRole() == "USER") {
            return "redirect:/user/" + userId;
        }
        else {
            model.addAttribute("admin", userService.getUserById(userId));
            model.addAttribute("product", productService.getProductById(productId));
            return "admin/editProduct";
        }
    }

    @PostMapping("/{userId}/product/{productId}/edit")
    public String editProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId, Product product) {
        productService.updateProduct(product);
        return "redirect:/admin/" + userId + "/product/";
    }

    @GetMapping("/{userId}/product/{productId}")
    public String getProduct(@PathVariable("userId") Long userId, @PathVariable("productId") Long productId, Model model) {
        model.addAttribute("admin", userService.getUserById(userId));
        model.addAttribute("product", productService.getProductById(productId));
        return "admin/viewProduct";
    }

    @GetMapping("/{userId}")
    public String getAdmin(@PathVariable("userId") Long userId) {
        return "redirect:/admin/" + userId + "/product/";
    }

    @GetMapping("/{userId}/order/")
    public String AdmingetAllOrders(@PathVariable("userId") Long userId, Model model) {
        model.addAttribute("admin", userService.getUserById(userId));
        model.addAttribute("orders", orderService.getAllOrders());
        return "admin/AdminviewOrders";
    }

    @GetMapping("/{userId}/order/{orderId}/delete")
    public String deleteOrder(@PathVariable("userId") Long userId, @PathVariable("orderId") Long orderId) {
        orderService.deleteOrderById(orderId);
        return "redirect:/admin/" + userId + "/order/";
    }

}
