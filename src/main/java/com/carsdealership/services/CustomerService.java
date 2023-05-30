package com.carsdealership.services;

import com.carsdealership.models.dtos.CustomerDTO;

import java.util.List;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    List<CustomerDTO> getAllCustomers();

    void deleteCustomerById(long id);

    CustomerDTO updateCustomerById(long id, CustomerDTO customerDTO);
}