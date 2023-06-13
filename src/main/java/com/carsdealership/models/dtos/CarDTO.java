package com.carsdealership.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class CarDTO implements Serializable {

    private long id;
    @NotBlank(message = "Car Brand must not be blank")
    @Size(min = 2, max = 20, message = "Car Brand must contain between 2 and 20 characters")
    private String carBrand;
    @NotBlank(message = "Car Model must not be blank")
    @Size(min = 2, max = 20, message = "Car Model must contain between 2 and 20 characters")
    private String carModel;
    @com.carsdealership.models.constraints.YearRange(message = "Year of manufacture must be between 1960 and the current year")
    private Integer year; //LocalDate
    @NotNull(message = "Price must not be null")
    @Positive(message = "Price must be a positive value")
    private double price;
}