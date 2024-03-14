package com.kavya.demo.Service;

import com.kavya.demo.model.Customer;
import com.kavya.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    // Generate a token for authentication
    public String generateToken() {
        // Generate a random UUID token
        return UUID.randomUUID().toString();
    }
    // Hash password (implement your hashing logic)
    private String hashPassword(String password) {
        // Implement password hashing logic here
        return password; // Dummy implementation, replace with actual hashing logic
    }

    public boolean isValidUser(String email, String password) {
        // Retrieve user from the database using the provided email
        Customer customer = customerRepository.findByEmail(email);
        // Check if a user with the provided email exists and if the password matches
        return customer != null && customer.getPassword().equals(hashPassword(password));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public Customer createCustomer(Customer customer) {
        // Hash password before saving
        customer.setPassword(hashPassword(customer.getPassword()));
        // Generate token for authentication
        String token = generateToken();
        customer.setToken(token);
        return customerRepository.save(customer);
    }

    public Customer getUserByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public Customer updateCustomer(Long id, Customer customer) {
        if (customerRepository.existsById(id)) {
            customer.setId(id);
            // Hash password before updating
            customer.setPassword(hashPassword(customer.getPassword()));
            return customerRepository.save(customer);
        }
        return null;
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
}
