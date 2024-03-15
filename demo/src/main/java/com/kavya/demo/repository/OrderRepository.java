package com.kavya.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kavya.demo.model.Orders; // Import the correct Order entity class

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    // You don't need to declare a save method here because JpaRepository already provides it
     Orders findFirstByCatererId(Long catererId);

    Orders findByCatererIdAndStatus(Long catererId, String status);
}

