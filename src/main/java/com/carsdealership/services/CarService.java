package com.carsdealership.services;

import com.carsdealership.models.dtos.CarDTO;

import java.util.List;

public interface CarService {
    CarDTO createCar(CarDTO carDTO);

    List<CarDTO> getAllCars();

    void deleteCarById(long CarId);
}