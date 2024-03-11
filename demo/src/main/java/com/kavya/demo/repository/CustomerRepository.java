package com.kavya.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kavya.demo.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
    Customer findByFullName(String FullName);
    Customer findByFullNameAndPassword(String fullName, String password);
}
