package com.carsdealership.integrationTesting;

import com.carsdealership.models.dtos.CustomerDTO;
import com.carsdealership.models.entities.Customer;
import com.carsdealership.repositories.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class CustomersServiceIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private CustomerDTO customerDTO;

    @MockBean
    private CustomerRepository customerRepository;

    @BeforeEach
    void setup() {
        customerDTO = CustomerDTO.builder()
                .firstName("Maria")
                .lastName("Ioana")
                .email("tuesticampioana@example.com")
                .build();
    }

    @Test
    void TestCreateCustomerShouldPass() throws Exception {
        Customer savedCustomer = objectMapper.convertValue(customerDTO, Customer.class);
        savedCustomer.setId(1L);
        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectToString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(customerDTO.getFirstName()))
                .andExpect(jsonPath("$.lastName").value(customerDTO.getLastName()))
                .andExpect(jsonPath("$.email").value(customerDTO.getEmail()));

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void TestAllCustomersShouldPass() throws Exception {
        Customer customer1 = Customer.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@example.com")
                .build();

        Customer customer2 = Customer.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .email("janesmith@example.com")
                .build();

        when(customerRepository.findAll()).thenReturn(List.of(customer1, customer2));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value(customer1.getFirstName()))
                .andExpect(jsonPath("$[0].lastName").value(customer1.getLastName()))
                .andExpect(jsonPath("$[0].email").value(customer1.getEmail()))
                .andExpect(jsonPath("$[1].firstName").value(customer2.getFirstName()))
                .andExpect(jsonPath("$[1].lastName").value(customer2.getLastName()))
                .andExpect(jsonPath("$[1].email").value(customer2.getEmail()));

        verify(customerRepository, times(1)).findAll();
    }

    private String objectToString(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException exception) {
            log.error("Error occurred during object serialization: {}", exception.getMessage());
        }
        return null;
    }
}