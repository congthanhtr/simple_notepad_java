package com.example.paymanagement.model;

import javax.persistence.CascadeType;
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
@Table(name = "money_in")
@NoArgsConstructor
@Getter @Setter
public class MoneyIn {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long money_in_id;

    private String date_time;

    private long amount_in;

    private String description;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    public MoneyIn(String date_time, long amount_in, String description, Category category, Chapter chapter) {
        this.date_time = date_time;
        this.amount_in = amount_in;
        this.description = description;
        this.category = category;
        this.chapter = chapter;
    }
}
