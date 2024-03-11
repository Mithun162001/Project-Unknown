package com.kavya.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.kavya.demo.Service.CatererService;
import com.kavya.demo.Service.OrderService;
import com.kavya.demo.model.Caterer;
import com.kavya.demo.model.Customer;
import com.kavya.demo.model.Orders;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CatererService catererService;

    @GetMapping("/makeOrder")
    public String showOrderForm(Model model, HttpServletRequest request) {
        // Get the logged-in user ID from the session
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userId") == null) {
            // Redirect to the login page if user is not logged in
            return "redirect:/login";
        }
        session.getAttribute("userId");

        // Fetch the caterer list from the database
        List<Caterer> catererList = catererService.getAllCaterers();

        // Add the caterer list to the model
        model.addAttribute("catererList", catererList);

        // Add a new Order object to the model
        model.addAttribute("order", new Orders());

        return "makeorder";
    }

    // @GetMapping("/trackOrder")
    // public String showLoginForm() {
    //     return "trackorder";
    // }



    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute("order") Orders order, HttpSession session) {
        // Get the logged-in user from the session
        Customer user = (Customer) session.getAttribute("user");
        if (user == null) {
            // Redirect to the login page if user is not logged in
            return "redirect:/login";
        }
    
        // Set the user on the order
        order.setUser(user);
    
        // Set price based on numberOfPeople
        double price = order.getNumberOfPeople() * 100;
        order.setPrice(price);
    
        // Save the order
        orderService.saveOrder(order);
    
        // Redirect to the track order page
        return "trackOrder";
    }
    
}
