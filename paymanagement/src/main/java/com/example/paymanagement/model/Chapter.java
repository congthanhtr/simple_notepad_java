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
@Table(name = "chapter")
@NoArgsConstructor
@Getter @Setter
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long chapter_id;

    // @Column(name = "chapter_name", unique = true)
    // @OneToOne(mappedBy = "chapter")
    private String chapter_name;

    private long total_in;
    private long total_out;
}
