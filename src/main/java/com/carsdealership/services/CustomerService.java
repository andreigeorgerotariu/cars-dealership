package com.carsdealership.services;

import com.carsdealership.models.dtos.CustomerDTO;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);
}