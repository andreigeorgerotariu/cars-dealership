package com.carsdealership.models.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Data
@Builder
@ToString
public class PurchaseDTO implements Serializable {

    private long id;
    private LocalDateTime purchaseDate;
    private double totalPrice;
    @Pattern(regexp = "^(cash|card)$", message = "Payment method must be either 'cash' or 'card'")
    private String paymentMethod;
    private long customerId;
    @JsonProperty
    private List<CarIdDTO> purchaseCarIdList;
}