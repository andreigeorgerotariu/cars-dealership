package com.carsdealership.unitTesting;

import com.carsdealership.models.dtos.CustomerDTO;
import com.carsdealership.models.entities.Customer;
import com.carsdealership.repositories.CustomerRepository;
import com.carsdealership.services.CustomerServiceImpl;
import com.carsdealership.services.CustomerValidationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private CustomerValidationService customerValidationService;
    @Mock
    private CustomerRepository customerRepository;

    @Test
    void testCreateCustomerShouldPass() {

        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName("Andrew")
                .lastName("Raynolds")
                .email("andrew@mail.com")
                .build();

        Customer customerEntity = Customer.builder()
                .firstName("Andrew")
                .lastName("Raynolds")
                .email("andrew@mail.com")
                .build();

        when(objectMapper.convertValue(customerDTO, Customer.class)).thenReturn(customerEntity);
        when(customerRepository.save(customerEntity)).thenReturn(customerEntity);
        when(objectMapper.convertValue(customerEntity, CustomerDTO.class)).thenReturn(customerDTO);

        CustomerDTO resultCustomerDTO = customerService.createCustomer(customerDTO);

        Assertions.assertEquals(customerDTO, resultCustomerDTO);
    }

    @Test
    void testCreateCustomerInvalidEmailShouldThrowException() {

        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName("Andrew")
                .lastName("Raynolds")
                .email("invalid_email")
                .build();

        Assertions.assertThrows(RuntimeException.class, () -> {
            customerService.createCustomer(customerDTO);
        });
    }
}