package com.carsdealership.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long CarId;
    @Column(name = "car_brand")
    private String carBrand;
    @Column(name = "car_model")
    private String carModel;
    @Column(name = "year")
    private int year;
    @Column(name = "price")
    private double price;
}