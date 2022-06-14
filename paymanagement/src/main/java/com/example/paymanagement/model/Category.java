package com.example.paymanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@NoArgsConstructor
@Getter @Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long category_id;
    
    // @Column(name = "category_name", unique = true)
    // @OneToOne(mappedBy = "category")
    private String category_name;

    public Category(String category_name) {
        this.category_name = category_name;
    }
}
