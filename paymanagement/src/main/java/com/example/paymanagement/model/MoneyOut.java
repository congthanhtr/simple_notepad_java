package com.example.paymanagement.model;

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
@Table(name = "money_out")
@NoArgsConstructor
@Getter
@Setter
public class MoneyOut {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long money_out_id;

    private String date_time;

    private long amount_out;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "chapter_id")
    private Chapter chapter;

    public MoneyOut(String date_time, long amount_out, String description, Category category, Chapter chapter) {
        this.date_time = date_time;
        this.amount_out = amount_out;
        this.description = description;
        this.category = category;
        this.chapter = chapter;
    }
}
