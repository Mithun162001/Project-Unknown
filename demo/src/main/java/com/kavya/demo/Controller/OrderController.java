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

import jakarta.servlet.http.HttpServletRequest;
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
    public String showOrderForm(Model model, HttpServletRequest request) {
        // Get the logged-in user ID from the session
        HttpSession session = request.getSession();
        // if (session == null || session.getAttribute("userId") == null) {
        //     // Redirect to the login page if user is not logged in
        //     return "redirect:/login";
        // }
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
public String saveOrder(@ModelAttribute("order") Orders order, HttpSession session, @RequestParam Long catererId) {
    // Get the logged-in user's email from the session
    String userEmail = (String) session.getAttribute("userId");
    // if (userEmail == null) {
    //     // Redirect to the login page if user is not logged in
    //     return "redirect:/login";
    // }

    // Retrieve the logged-in user based on their email
    Customer user = customerService.getUserByEmail(userEmail);
    // if (user == null) {
    //     // Handle scenario where user is not found
    //     return "error";
    // }

    // Set the user on the order
    order.setUser(user);

    // Fetch the caterer based on catererId
    Caterer caterer = catererService.getCatererById(catererId);
    // if (caterer == null) {
    //     // Handle caterer not found scenario
    //     return "error";
    // }

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
        dishService.saveDish(dish); // Save dish to the database

        // Save the dish name in the Orders table
        // if (order.getDishName() == null || order.getDishName().isEmpty()) {
        //     order.setDishName(dishName.trim());
        // } else {
        //     order.setDishName(order.getDishName() + ", " + dishName.trim());
        // }
    }

    // Update the order with the concatenated dish names
    orderService.saveOrder(order);

    // Redirect to the track order page
    return "trackOrder";
}


    
}
