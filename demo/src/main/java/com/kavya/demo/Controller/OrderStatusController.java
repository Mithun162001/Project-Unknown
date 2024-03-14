package com.kavya.demo.Controller;

import java.util.List;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kavya.demo.Service.OrderService;
import com.kavya.demo.model.Orders;

import jakarta.servlet.http.HttpSession;

// Import statements for Spring MVC, Services, Models, etc.

@Controller
public class OrderStatusController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/Caterer/OrderStatus")
    public String showOrderStatus(Model model, HttpSession session) {
        Long catererId = (Long) session.getAttribute("catererId");
        List<Orders> orders = orderService.getOrdersByCatererIdAndStatusPending(catererId);
        model.addAttribute("orders", orders);
        return "catererOrderStatus";
    }

    @PostMapping("/Caterer/OrderStatus")
    public String saveOrderStatus(@RequestParam("orderId") List<Long> orderIds,
                                  @RequestParam("status") List<String> statuses,
                                  @RequestParam("price") List<Double> prices) {
        for (int i = 0; i < orderIds.size(); i++) {
            orderService.updateOrderStatusAndPrice(orderIds.get(i), statuses.get(i), prices.get(i));
        }
        return "redirect:/Caterer/OrderStatus";
    }

    // Add any additional methods as necessary for the controller
}
