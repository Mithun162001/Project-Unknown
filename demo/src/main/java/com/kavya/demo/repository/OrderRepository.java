package com.kavya.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kavya.demo.model.Orders; // Import the correct Order entity class

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
    // You don't need to declare a save method here because JpaRepository already provides it
    List<Orders> findByCatererId(Long catererId);

    List<Orders> findByCatererIdAndStatus(Long catererId, String status);
}

