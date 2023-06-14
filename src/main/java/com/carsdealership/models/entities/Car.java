package com.carsdealership.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "car_brand")
    private String carBrand;
    @Column(name = "car_model")
    private String carModel;
    @Column(name = "year")
    private Integer year;
    @Column(name = "price")
    private double price;

    @ManyToMany(mappedBy = "cars", fetch = FetchType.LAZY)
    private Set<Purchase> purchases = new HashSet<>();
}