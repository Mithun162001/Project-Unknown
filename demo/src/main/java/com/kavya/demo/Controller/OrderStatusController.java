package com.kavya.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import com.kavya.demo.Service.OrderService;
import com.kavya.demo.model.Orders;
import java.io.IOException;

@Controller
public class OrderStatusController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/Caterer/OrderStatus")
    public void showOrderStatus(HttpServletResponse response, HttpSession session) throws IOException {
        // Retrieve the caterer ID associated with the logged-in session
        Long catererId = (Long) session.getAttribute("catererId");

        // Fetch orders for the logged-in caterer based on catererId
        Orders order = orderService.getOrdersByCatererId(catererId);

        // Check if order exists
        if (order != null) {
            // Write order details directly to the response
            response.getWriter().println("Order ID: " + order.getOrderId());
            response.getWriter().println("Dish Name: " + order.getDishName());
            response.getWriter().println("Delivery Date: " + order.getDeliveryDate());
            response.getWriter().println("Number of People: " + order.getNumberOfPeople());
         
            response.getWriter().println("Status: " + order.getStatus());
            response.getWriter().println("Price: " + order.getPrize());
        } else {
            // If order not found, send a message
            response.getWriter().println("No orders found for this caterer.");
        }
    }
}
