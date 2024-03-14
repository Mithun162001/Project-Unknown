package com.kavya.demo.Controller;

import com.kavya.demo.Service.OrderService;
import com.kavya.demo.model.Orders;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrderStatusController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/Caterer/orderStatus")
    public String showOrderStatus(Model model, HttpSession session) {
        Long catererId = (Long) session.getAttribute("catererId");
        List<Orders> orders = orderService.getOrdersByCatererIdAndStatusPending(catererId);
        model.addAttribute("orders", orders);
        return "catererOrderStatus";
    }

    @PostMapping("/Caterer/OrderStatus")
    public String saveOrderStatus(@RequestParam Long orderId, @RequestParam String status, @RequestParam double price) {
        orderService.updateOrderStatusAndPrice(orderId, status, price);
        return "catererOrderStatus";
    }
}
