package com.carsdealership.services;

import com.carsdealership.models.dtos.CustomerDTO;
import com.carsdealership.models.entities.Customer;
import com.carsdealership.repositories.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

    private final ObjectMapper objectMapper;
    private final CustomerValidationService customerValidationService;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(ObjectMapper objectMapper,
                               CustomerRepository customerRepository,
                               CustomerValidationService customerValidationService) {
        this.objectMapper = objectMapper;
        this.customerRepository = customerRepository;
        this.customerValidationService = customerValidationService;
    }

    @Transactional
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        customerValidationService.emailValidation(customerDTO.getEmail());
        Customer customer = objectMapper.convertValue(customerDTO, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer " + savedCustomer.getFirstName() + " was created");
        return objectMapper.convertValue(savedCustomer, CustomerDTO.class);
    }
}