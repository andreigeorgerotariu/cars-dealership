package com.carsdealership.models.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@Builder
@ToString
public class CustomerDTO implements Serializable {

    private long id;
    @NotBlank(message = "First name must not be blank")
    @Pattern(regexp = "^[a-zA-Z\\-\\s]*$", message = "First name contains invalid characters")
    @Size(min = 2, max = 20, message = "First name must contain between 2 and 20 characters" )
    private String firstName;
    @NotBlank(message = "Last name must not be blank")
    @Pattern(regexp = "^[a-zA-Z\\-\\s]*$", message = "Last name contains invalid characters")
    @Size(min = 2, max = 20, message = "Last name must contain between 2 and 20 characters" )
    private String lastName;
    @NotBlank(message = "Email must not be blank")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Email is not valid")
    private String email;
}