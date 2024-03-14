package com.kavya.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kavya.demo.Service.CustomerService;
import com.kavya.demo.model.Customer;

import jakarta.servlet.http.HttpSession;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "signup";
    }

    @PostMapping("/signup")
    public String createCustomer(@ModelAttribute Customer customer, Model model) {
        // Generate a token for the customer
        String token = customerService.generateToken();
        customer.setToken(token);
        
        customerService.createCustomer(customer);
        String registrationSuccessMessage = "Registration successful. Please login.";
        model.addAttribute("successMessage", registrationSuccessMessage);
        return "login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        // Check if the email and password are valid
        if (customerService.isValidUser(email, password)) {
            // Retrieve the customer from the database
            Customer customer = customerService.getUserByEmail(email);
            // Store user's email and token in session
            session.setAttribute("userEmail", email);
            session.setAttribute("token", customer.getToken());
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
}
