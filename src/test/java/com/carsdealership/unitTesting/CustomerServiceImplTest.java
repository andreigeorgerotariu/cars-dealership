package com.carsdealership.unitTesting;

import com.carsdealership.exceptions.CustomerNotFoundException;
import com.carsdealership.models.dtos.CustomerDTO;
import com.carsdealership.models.entities.Customer;
import com.carsdealership.repositories.CustomerRepository;
import com.carsdealership.services.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceImplTest {

    private CustomerServiceImpl customerService;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(objectMapper, customerRepository);
    }

    @Test
    void TestCreateCustomer_ValidCustomerDTO_ReturnsCustomerDTO() {
        CustomerDTO.CustomerDTOBuilder builder = CustomerDTO.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phoneNumber("1234567890")
                .yearOfBirth(1990)
                .address("Address")
                .city("New York");
        CustomerDTO customerDTO = builder.build();
        Customer customer = new Customer();
        when(objectMapper.convertValue(customerDTO, Customer.class)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(objectMapper.convertValue(customer, CustomerDTO.class)).thenReturn(customerDTO);

        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);

        assertNotNull(createdCustomer);
        assertEquals(customerDTO, createdCustomer);
    }

    @Test
    void TestCreateCustomer_ValidCustomerDTO_ReturnsCreatedCustomerDTO() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .phoneNumber("1234567890")
                .build();

        Customer customer = Customer.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .phoneNumber("1234567890")
                .build();

        Customer savedCustomer = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .phoneNumber("1234567890")
                .build();

        when(objectMapper.convertValue(customerDTO, Customer.class)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(savedCustomer);
        when(objectMapper.convertValue(savedCustomer, CustomerDTO.class)).thenReturn(customerDTO);

        CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);

        assertNotNull(createdCustomer);
        assertEquals(customerDTO.getFirstName(), createdCustomer.getFirstName());
        assertEquals(customerDTO.getLastName(), createdCustomer.getLastName());
        assertEquals(customerDTO.getEmail(), createdCustomer.getEmail());
        assertEquals(customerDTO.getPhoneNumber(), createdCustomer.getPhoneNumber());
    }

    @Test
    void TestCreateCustomer_InvalidCustomerDTO_ThrowsException() {
        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("john@example.com")
                .build();

        when(objectMapper.convertValue(customerDTO, Customer.class)).thenThrow(IllegalArgumentException.class);

        assertThrows(IllegalArgumentException.class, () -> customerService.createCustomer(customerDTO));
    }

    @Test
    void TestGetAllCustomers_NoCustomers_ReturnsEmptyList() {
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());

        List<CustomerDTO> customers = customerService.getAllCustomers();

        assertNotNull(customers);
        assertTrue(customers.isEmpty());
    }

    @Test
    void TestDeleteCustomerById_ExistingCustomerId_DeletesCustomer() {
        long customerId = 1L;
        when(customerRepository.existsById(customerId)).thenReturn(true);

        assertDoesNotThrow(() -> customerService.deleteCustomerById(customerId));

        verify(customerRepository, times(1)).deleteById(customerId);
    }

    @Test
    void TestDeleteCustomerById_NonExistingCustomerId_ThrowsCustomerNotFoundException() {
        long customerId = 1L;
        when(customerRepository.existsById(customerId)).thenReturn(false);

        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteCustomerById(customerId));
    }

    @Test
    void TestUpdateCustomerById_ExistingCustomerIdAndValidCustomerDTO_ReturnsUpdatedCustomerDTO() {
        long customerId = 1L;
        CustomerDTO.CustomerDTOBuilder builder = CustomerDTO.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .phoneNumber("1234567890")
                .yearOfBirth(1990)
                .address("Address")
                .city("New York");
        CustomerDTO customerDTO = builder.build();
        Customer customer = new Customer();
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(objectMapper.convertValue(customer, CustomerDTO.class)).thenReturn(customerDTO);
        when(customerRepository.save(customer)).thenReturn(customer);

        CustomerDTO updatedCustomer = customerService.updateCustomerById(customerId, customerDTO);

        assertNotNull(updatedCustomer);
        assertEquals(customerDTO, updatedCustomer);
    }

    @Test
    void TestUpdateCustomerById_NonExistingCustomerId_ThrowsCustomerNotFoundException() {
        long customerId = 1L;
        CustomerDTO customerDTO = CustomerDTO.builder().build();
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(CustomerNotFoundException.class, () -> customerService.updateCustomerById(customerId, customerDTO));
    }
}