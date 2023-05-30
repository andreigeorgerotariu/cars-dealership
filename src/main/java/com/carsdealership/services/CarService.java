package com.carsdealership.services;

import com.carsdealership.models.dtos.CarDTO;

public interface CarService {
    CarDTO createCar(CarDTO carDTO);
}