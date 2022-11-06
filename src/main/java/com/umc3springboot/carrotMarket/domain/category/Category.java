package com.umc3springboot.carrotMarket.domain.category;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name="Category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="categoryIdx")
    private Long id;

    @Column(nullable = false)
    private String name;
}
