package com.carsdealership.services;

import com.carsdealership.models.dtos.CarDTO;

import java.math.BigDecimal;
import java.util.List;

public interface CarService {
    CarDTO createCar(CarDTO carDTO);

    List<CarDTO> getAllCars();

    void deleteCarById(long carId);

    CarDTO updateCarById(long carId, CarDTO carDTO);

    List<CarDTO> findCarByCarBrandAndCarModelAndYearAndPrice(String carBrand, String carModel, Integer year, BigDecimal minPrice, BigDecimal maxPrice);

    List<CarDTO> findCarByCarBrandAndCarModelAndYear(String carBrand, String carModel, Integer year);

    List<CarDTO> findCarByCarBrandAndCarModel(String carBrand, String carModel);
}