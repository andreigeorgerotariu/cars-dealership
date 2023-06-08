package com.carsdealership.unitTesting;

import com.carsdealership.models.dtos.CustomerDTO;
import com.carsdealership.models.entities.Customer;
import com.carsdealership.repositories.CustomerRepository;
import com.carsdealership.services.CustomerServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
class CustomerServiceTest {

    @InjectMocks
    private CustomerServiceImpl customerService;
    @Mock
    private ObjectMapper objectMapper;
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

    @Test
    void testGetAllCustomersShouldPass() {
        // GIVEN
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder()
                .firstName("Andrew")
                .lastName("Raynolds")
                .email("andrew@mail.com")
                .build());
        customers.add(Customer.builder()
                .firstName("Andrew")
                .lastName("Raynolds")
                .email("andrew@mail.com")
                .build());

        List<CustomerDTO> expectedCustomerDTOs = new ArrayList<>();
        expectedCustomerDTOs.add(CustomerDTO.builder()
                .firstName("Andrew")
                .lastName("Raynolds")
                .email("andrew@mail.com")
                .build());
        expectedCustomerDTOs.add(CustomerDTO.builder()
                .firstName("Andrew")
                .lastName("Raynolds")
                .email("andrew@mail.com")
                .build());

        when(customerRepository.findAll()).thenReturn(customers);

        Mockito.when(objectMapper.convertValue(Mockito.any(Customer.class), Mockito.eq(CustomerDTO.class)))
                .thenAnswer(invocation -> {
                    Customer customer = invocation.getArgument(0);
                    return CustomerDTO.builder()
                            .firstName(customer.getFirstName())
                            .lastName(customer.getLastName())
                            .email(customer.getEmail())
                            .build();
                });

        // WHEN
        List<CustomerDTO> result = customerService.getAllCustomers();

        // THEN
        Assertions.assertEquals(expectedCustomerDTOs, result);
    }

    @Test
    void testGetAllCustomersShouldThrowException() {
        // GIVEN
        when(customerRepository.findAll()).thenReturn(new ArrayList<>());

        // WHEN
        List<CustomerDTO> result = customerService.getAllCustomers();

        // THEN
        Assertions.assertTrue(result.isEmpty());
    }
}