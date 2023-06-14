package com.carsdealership.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "purchases")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "purchase_date")
    private LocalDateTime purchaseDate;
    @Column(name = "total_price")
    private double totalPrice;
    @Column(name = "payment_method")
    private String paymentMethod;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "purchases_cars",
            joinColumns = @JoinColumn(name = "purchase_id"),
            inverseJoinColumns = @JoinColumn(name = "car_id"))
    private Set<Car> cars = new HashSet<>();

    @Override
    public String toString() {
        return "Purchase{" +
                "id=" + id +
                ", purchaseDate=" + purchaseDate +
                ", totalPrice=" + totalPrice +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", customerId=" + (customer != null ? customer.getId() : null) +
                '}';
    }
}