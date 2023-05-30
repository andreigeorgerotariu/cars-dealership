package com.carsdealership.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class CarDTO implements Serializable {

    private long carId;
    @NotBlank(message = "Car Brand must not be blank")
    @Size(min = 2, max = 20, message = "Car Brand must contain between 2 and 20 characters")
    private String carBrand;
    @NotBlank(message = "Car Model must not be blank")
    @Size(min = 2, max = 20, message = "Car Model must contain between 2 and 20 characters")
    private String carModel;
    @NotEmpty(message = "Year of manufacture must not be empty")
    private int year;
    @NotEmpty(message = "Price must not be blank")
    private double price;
}