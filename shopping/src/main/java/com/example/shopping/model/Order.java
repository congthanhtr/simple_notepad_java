package com.example.shopping.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long orderId;

    @ManyToOne()
    @JoinColumn(name = "userId")
    private MyUser myUser;

    @ManyToOne()
    @JoinColumn(name = "productId")
    private Product product;

    private int quantity;

    public Order(MyUser myUser, Product product, int quantity) {
        this.myUser = myUser;
        this.product = product;
        this.quantity = quantity;
    }
}
