package com.kavya.demo.Service;

import com.kavya.demo.model.Customer;
import com.kavya.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    public boolean isValidUser(String email, String password) {
        // Retrieve user from the database using the provided email
        Customer customer = customerRepository.findByEmail(email);
        // Check if a user with the provided email exists and if the password matches
        return customer != null && customer.getPassword().equals(password);
    }
    

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @SuppressWarnings("null")
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @SuppressWarnings("null")
    public Customer createCustomer(Customer user) {
        return customerRepository.save(user);
    }

    public Customer getUserByEmail(String email) {
        return customerRepository.findByEmail(email);
    }


    @SuppressWarnings("null")
    public Customer updateCustomer(Long id, Customer user) {
        if (customerRepository.existsById(id)) {
            user.setId(id);
            return customerRepository.save(user);
        }
        return null;
    }

    @SuppressWarnings("null")
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public static Customer saveCustomer(Customer customer) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveCustomer'");
    }
    
}
