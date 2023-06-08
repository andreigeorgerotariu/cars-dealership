package com.carsdealership.services;

import com.carsdealership.exceptions.CustomerNotFoundException;
import com.carsdealership.models.dtos.CustomerDTO;
import com.carsdealership.models.entities.Customer;
import com.carsdealership.repositories.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

    private final ObjectMapper objectMapper;
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(ObjectMapper objectMapper,
                               CustomerRepository customerRepository) {
        this.objectMapper = objectMapper;
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = objectMapper.convertValue(customerDTO, Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        log.info("Customer " + savedCustomer.getFirstName() + " was successfully created.");
        return objectMapper.convertValue(savedCustomer, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customersFound = customerRepository.findAll();
        List<CustomerDTO> customersFoundDTO = new ArrayList<>();
        customersFound.forEach(customer -> customersFoundDTO.add(objectMapper.convertValue(customer, CustomerDTO.class)));
        return customersFoundDTO;
    }

    @Override
    public void deleteCustomerById(long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            log.info("Customer with id " + id + " was successfully deleted.");
        } else {
            throw new CustomerNotFoundException("Customer not found.");
        }
    }

    @Override
    public CustomerDTO updateCustomerById(long id, CustomerDTO customerDTO) {
        Customer customerFound = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer with id " + id + " does not exist."));
        customerFound.setFirstName(customerDTO.getFirstName());
        customerFound.setLastName(customerDTO.getLastName());
        customerFound.setEmail(customerDTO.getEmail());
        Customer customerSaved = customerRepository.save(customerFound);
        log.info("Customer with id " + id + " was successfully updated");
        return objectMapper.convertValue(customerSaved, CustomerDTO.class);
    }
}