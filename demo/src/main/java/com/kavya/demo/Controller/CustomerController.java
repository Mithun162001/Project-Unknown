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
        model.addAttribute("successMessage", "Registration successful. Please login.");
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    // Separate API-like login handling
    @PostMapping("/login")
    public String login(@RequestParam String fullName, @RequestParam String password, HttpSession session, Model model) {
        Customer user = userService.authenticateUser(fullName, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/customer_welcome";
        } else {
            model.addAttribute("errorMessage", "Invalid credentials");
            return "login";
        }
    }
    

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    @GetMapping("/customer_service")
    public String goToCustomerServicePage() {
        return "customer_service";
    }

    // Assuming below endpoints are part of an API, consider moving to @RestController
    @GetMapping("/customersinfo")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(userService.getAllCustomers());
    }

    @GetMapping("/customerinfo/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = userService.getCustomerById(id);
        return customer != null ? ResponseEntity.ok(customer) : ResponseEntity.notFound().build();
    }

    @PutMapping("/updatecustomer/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = userService.updateCustomer(id, customer);
        return updatedCustomer != null ? ResponseEntity.ok(updatedCustomer) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletecustomer/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        userService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
