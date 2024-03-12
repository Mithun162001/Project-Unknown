package com.kavya.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kavya.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByEmail(String email);
}

