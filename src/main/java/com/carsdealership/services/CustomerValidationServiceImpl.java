package com.carsdealership.services;

import com.carsdealership.exceptions.CustomerValidationException;
import com.carsdealership.models.dtos.CustomerDTO;
import org.springframework.stereotype.Service;

@Service
public class CustomerValidationServiceImpl implements CustomerValidationService {

    @Override
    public void validateCustomerDTO(CustomerDTO customerDTO) {
        if (customerDTO == null) {
            throw new CustomerValidationException("Customer data cannot be null");
        }

        if (isNullOrEmpty(customerDTO.getFirstName())) {
            throw new CustomerValidationException("First name must not be blank");
        }

        if (customerDTO.getFirstName().length() < 2 || customerDTO.getFirstName().length() > 20) {
            throw new CustomerValidationException("First name must contain between 2 and 20 characters");
        }

        if (isNullOrEmpty(customerDTO.getLastName())) {
            throw new CustomerValidationException("Last name must not be blank");
        }

        if (customerDTO.getLastName().length() < 2 || customerDTO.getLastName().length() > 20) {
            throw new CustomerValidationException("Last name must contain between 2 and 20 characters");
        }

        if (isNullOrEmpty(customerDTO.getEmail())) {
            throw new CustomerValidationException("Email must not be blank");
        }

        if (!customerDTO.getEmail().matches("[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")) {
            throw new CustomerValidationException("Email is not valid");
        }
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}