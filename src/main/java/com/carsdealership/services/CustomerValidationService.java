package com.carsdealership.services;

import com.carsdealership.models.dtos.CustomerDTO;

public interface CustomerValidationService {

    void validateCustomerDTO(CustomerDTO customerDTO);
}