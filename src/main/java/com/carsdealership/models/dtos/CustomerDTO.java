package com.carsdealership.models.dtos;

import com.carsdealership.models.constrains.AgeAboveOrEqual;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Phone number must not be blank")
    @Pattern(regexp = "\\d{10}", message = "Phone number must be a 10 digit number")
    private String phoneNumber;

    @NotNull(message = "Year of birth must not be null")
    @AgeAboveOrEqual(message = "Customer must be at least 18 years old")
    private Integer yearOfBirth;

    @NotBlank(message = "Address must not be blank.")
    @Pattern(regexp = "^[a-zA-Z0-9\\-\\s.,]*$", message = "Address contains invalid characters")
    @Size(min = 2, max = 50, message = "Address must contain between 2 and 50 characters" )
    private String address;


    @NotBlank(message = "City must not be blank.")
    @Pattern(regexp = "^[a-zA-Z\\-\\s]*$", message = "City contains invalid characters")
    @Size(min = 2, max = 20, message = "City must contain between 2 and 20 characters" )
    private String city;
}
