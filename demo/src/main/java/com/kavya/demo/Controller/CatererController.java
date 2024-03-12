package com.kavya.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kavya.demo.Service.CatererService;
import com.kavya.demo.model.Caterer;

import jakarta.servlet.http.HttpSession;

@Controller
public class CatererController {

    @Autowired
    private CatererService catererService;

    @PostMapping("/catererSignup")
    public String createCaterer(@ModelAttribute("caterer") Caterer caterer, Model model) {
        // Create the caterer using the service method
        catererService.createCaterer(caterer);
        
        // Add success message
        String registrationSuccessMessage = "Caterer registration successful. Please login.";
        model.addAttribute("successMessage", registrationSuccessMessage);
        
        // Redirect to the login page
        return "caterer_login";
    }
    
    @GetMapping("/catererSignup")
public String showCatererSignupForm(Model model) {
    model.addAttribute("caterer", new Caterer());
    return "caterer_signup";
}

    @GetMapping("/catererLogin")
    public String showLoginForm() {
        return "caterer_login";
    }


    @GetMapping("/catererWelcome")
    public String showLoginForm2() {
        return "caterer_welcome";
    }


    @PostMapping("/catererLogin")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        if (catererService.isValidCaterer(email, password)) {
            session.setAttribute("userId", email);
            return "caterer_welcome";
        } else {
            String loginFailed = "Invalid credentials";
            model.addAttribute("errorMessage", loginFailed);
            return "caterer_login";
        }
    }
    

    // @GetMapping("/caterers/{id}")
    // public String getCatererById(@PathVariable Long id, Model model) {
    //     Caterer caterer = catererService.getCatererById(id);
    //     model.addAttribute("caterer", caterer);
    //     return "caterer_details"; // assuming you have a view named caterer_details to display caterer details
    // }

    // @PutMapping("/caterers/{id}")
    // public String updateCaterer(@PathVariable Long id, @ModelAttribute("user") Caterer user, Model model) {
    //     // Update caterer logic
    //     return "redirect:/caterers/" + id; // Redirect to the caterer details page
    // }

    // @DeleteMapping("/caterers/{id}")
    // public String deleteCaterer(@PathVariable Long id) {
    //     // Delete caterer logic
    //     return "redirect:/"; // Redirect to home or any other appropriate page
    // }
}
