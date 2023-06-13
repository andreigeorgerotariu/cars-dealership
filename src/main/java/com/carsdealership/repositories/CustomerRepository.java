package com.carsdealership.repositories;

import com.carsdealership.models.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c FROM Customer c " +
            "WHERE (:firstName IS NULL OR c.firstName = :firstName) " +
            "AND (:lastName IS NULL OR c.lastName = :lastName) " +
            "AND (:email IS NULL OR c.email = :email) " +
            "AND (:phoneNumber IS NULL OR c.phoneNumber = :phoneNumber) " +
            "AND (:city IS NULL OR c.city = :city)")
    List<Customer> searchCustomers(String firstName, String lastName, String email, String phoneNumber, String city);
}