package com.carsdealership.services;

import com.carsdealership.models.dtos.CarDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {
    CarDTO createCar(CarDTO carDTO);

    List<CarDTO> getAllCars();

    void deleteCarById(long carId);

    CarDTO updateCarById(long carId, CarDTO carDTO);

    List<CarDTO> searchCars(String carBrand, String carModel, Integer minYear, Integer maxYear,
                            BigDecimal minPrice, BigDecimal maxPrice);
}