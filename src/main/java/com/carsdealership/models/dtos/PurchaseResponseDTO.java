package com.carsdealership.models.dtos;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


@Data
public class PurchaseResponseDTO implements Serializable {

    private long id;
    private LocalDateTime purchaseDate;
    private double totalPrice;
    private String paymentMethod;
    private long customerId;
    private List<CarDTO> carList;
}
