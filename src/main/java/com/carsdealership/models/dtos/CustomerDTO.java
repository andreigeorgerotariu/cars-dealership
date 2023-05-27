package com.carsdealership.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class CustomerDTO implements Serializable {


    private Long id;
    @NotBlank(message = "first name must not be blank")
    private String firstName;
    @NotBlank(message = "last name must not be blank")
    private String lastName;
    @NotBlank(message = "email must not be blank")
    private String email;
}