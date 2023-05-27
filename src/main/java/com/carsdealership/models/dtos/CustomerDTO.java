package com.carsdealership.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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
    @Size(min = 2, max = 20, message = "first name must contain between 2 and 20 characters" )
    private String firstName;
    @NotBlank(message = "last name must not be blank")
    @Size(min = 2, max = 20, message = "first name must contain between 2 and 20 characters" )
    private String lastName;
    @NotBlank(message = "email must not be blank")
    private String email;
}