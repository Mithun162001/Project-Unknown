package com.kavya.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.kavya.demo.Service.CatererService;
import com.kavya.demo.Service.CustomerService;
import com.kavya.demo.Service.DishService;
import com.kavya.demo.Service.OrderService;
import com.kavya.demo.model.Caterer;
import com.kavya.demo.model.Customer;
import com.kavya.demo.model.Dish;
import com.kavya.demo.model.Orders;

import jakarta.servlet.http.HttpSession;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CatererService catererService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DishService dishService;

    @GetMapping("/makeOrder")
    public String showOrderForm(Model model, HttpSession session) {
        // Check if the user is logged in by checking if email is present in session
        String userEmail = (String) session.getAttribute("userEmail");
        if (userEmail == null) {
            // If not logged in, redirect to the login page
            return "redirect:/login";
        }

        // Retrieve the customer from the database based on the email
        Customer user = customerService.getUserByEmail(userEmail);
        if (user == null) {
            // If user not found, handle the scenario appropriately (e.g., redirect to login page with an error message)
            return "redirect:/login";
        }

        // Set session attributes for customerId and token
        session.setAttribute("customerId", user.getId());
        session.setAttribute("token", user.getToken());

        // Fetch the caterer list from the database
        List<Caterer> catererList = catererService.getAllCaterers();

        // Add the caterer list to the model
        model.addAttribute("catererList", catererList);

        // Add a new Order object to the model
        model.addAttribute("order", new Orders());

        return "makeorder";
    }

    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute("order") Orders order, HttpSession session, @RequestParam Long catererId) {
        // Get the logged-in user's ID from the session
        Long customerId = (Long) session.getAttribute("customerId");

        // Retrieve the customer based on their ID
        Customer user = customerService.getCustomerById(customerId);

        // Set the user on the order
        order.setUser(user);

        // Fetch the caterer based on catererId
        Caterer caterer = catererService.getCatererById(catererId);

        // Set the caterer on the order
        order.setCaterer(caterer);

        // Save the order
        orderService.saveOrder(order);

        // Process dish names and save them as separate entries
        String[] dishNames = order.getDishName().split(",");
        for (String dishName : dishNames) {
            Dish dish = new Dish();
            dish.setDishName(dishName.trim()); // Trim to remove any leading or trailing whitespaces
            dish.setOrder(order); // Set the order for the dish
            // Save the dish using the dish service
            dishService.saveDish(dish);
        }

        // Redirect to the track order page
        return "trackOrder";
    }
}
