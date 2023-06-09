package com.carsdealership.controllers;

import com.carsdealership.models.dtos.CustomerDTO;
import com.carsdealership.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.createCustomer(customerDTO));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable long id) {
        customerService.deleteCustomerById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDTO> updateCustomerById(@PathVariable long id, @RequestBody @Valid CustomerDTO customerDTO) {
        return ResponseEntity.ok(customerService.updateCustomerById(id, customerDTO));
    }

    @GetMapping("/search")
    public ResponseEntity<List<CustomerDTO>> searchCustomers(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
            @RequestParam(value = "city", required = false) String city) {

        List<CustomerDTO> customers = customerService.searchCustomers(firstName, lastName, email, phoneNumber, city);
        return ResponseEntity.ok(customers);
    }
}