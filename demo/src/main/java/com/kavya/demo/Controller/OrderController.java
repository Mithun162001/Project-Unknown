package com.kavya.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import jakarta.servlet.http.HttpSession;

import com.kavya.demo.Service.CatererService;
import com.kavya.demo.Service.OrderService;
import com.kavya.demo.model.Caterer;
import com.kavya.demo.model.Customer;
import com.kavya.demo.model.Orders;

import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CatererService catererService;

    /**
     * Displays the order form to the user, ensuring they are logged in.
     * If the user is not logged in, redirects to the login page.
     *
     * @param model the Spring model to pass data to the view
     * @param session the HTTP session for checking user login
     * @return the name of the template to render
     */
   @GetMapping("/makeOrder")
public String showOrderForm(Model model, @SessionAttribute("user") Customer user) {
    List<Caterer> catererList = catererService.getAllCaterers();
    model.addAttribute("catererList", catererList);
    model.addAttribute("order", new Orders());
    return "makeorder";
}


    /**
     * Processes the submitted order form, saving the order to the database.
     * Requires the user to be logged in.
     *
     * @param order the order data bound from the form submission
     * @param session the HTTP session for retrieving the logged-in user
     * @return a redirect string to the next view
     */
    @PostMapping("/saveOrder")
    public String saveOrder(@ModelAttribute("order") Orders order, HttpSession session) {
        // Ensure the user is logged in
        Customer user = (Customer) session.getAttribute("user");
        if (user == null) {
            // Redirect to login page if user not in session
            return "redirect:/login";
        }

        order.setUser(user);

        // The saveOrder method in OrderService already calculates the price
        orderService.saveOrder(order);

        // Redirect to a confirmation or tracking page (assuming you have one)
        return "redirect:/trackOrder";
    }
}
