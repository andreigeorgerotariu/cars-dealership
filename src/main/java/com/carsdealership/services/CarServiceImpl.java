package com.carsdealership.services;

import com.carsdealership.models.dtos.CarDTO;
import com.carsdealership.models.entities.Car;
import com.carsdealership.repositories.CarRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CarServiceImpl implements CarService {

    private final ObjectMapper objectMapper;
    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(ObjectMapper objectMapper, CarRepository carRepository) {
        this.objectMapper = objectMapper;
        this.carRepository = carRepository;
    }

    @Transactional
    @Override
    public CarDTO createCar(CarDTO carDTO) {
        Car car = objectMapper.convertValue(carDTO, Car.class);
        Car savedCar = carRepository.save(car);
        log.info("Car " + savedCar.getCarBrand() + " model " + savedCar.getCarModel() +
                " from year " + savedCar.getYear() + " was successfully created");
        return objectMapper.convertValue(savedCar, CarDTO.class);
    }

    @Override
    public List<CarDTO> getAllCars() {
        List<Car>carsFound = carRepository.findAll();
        List<CarDTO> carsFoundDTO = new ArrayList<>();
        carsFound.forEach(car -> carsFoundDTO.add(objectMapper.convertValue(car, CarDTO.class)));
        return carsFoundDTO;
    }
}