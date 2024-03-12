package com.kavya.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kavya.demo.model.Customer;
import com.kavya.demo.Service.CustomerService;

import jakarta.servlet.http.HttpSession;

import java.util.List;


@Controller
public class CustomerController {

    @Autowired
    private CustomerService userService;

    

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "signup";
    }
    
    @PostMapping("/signup")
    public String createCustomer(@ModelAttribute Customer customer, Model model) {
        userService.createCustomer(customer);
        String registrationSuccessMessage = "Registration successful. Please login.";
        model.addAttribute("successMessage", registrationSuccessMessage);
        return "login";
    }
    
    // @GetMapping("/")
    // public String showLoginForm2() {
    //     return "login";
    // }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
    // Check if the email and password are valid
    if (userService.isValidUser(email, password)) {
        // Store user ID in session
        session.setAttribute("userId", email);
        // Redirect to the home page or any other page
        return "customer_welcome";
    } else {
        String loginFailed = "Invalid credentials";
        model.addAttribute("errorMessage", loginFailed);
        return "login";
    }
}


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalidate session and logout
        session.invalidate();
        return "redirect:/login";
    }

   @GetMapping("/customer_service")
    public String goToCustomerServicePage() {
        return "customer_service";
    }
    

   @GetMapping("/customersinfo")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = userService.getAllCustomers();
        return ResponseEntity.ok().body(customers);
    }

    @GetMapping("/customerinfo/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = userService.getCustomerById(id);
        if (customer != null) {
            return ResponseEntity.ok().body(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/updatecustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer user) {
        Customer updatedCustomer = userService.updateCustomer(id, user);
        if (updatedCustomer != null) {
            return ResponseEntity.ok().body(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deletecustomer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        userService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
