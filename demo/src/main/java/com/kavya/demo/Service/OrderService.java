package com.kavya.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kavya.demo.model.Orders;
import com.kavya.demo.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public void saveOrder(com.kavya.demo.model.Orders order) {
        orderRepository.save(order);
    }
      public List<Orders> getOrdersByCatererId(Long catererId) {
        return orderRepository.findByCatererId(catererId);
    }

    public void updateOrderStatusAndPrice(Long orderId, String status, double price) {
        Orders order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            order.setPrize(price);
            orderRepository.save(order);
        }
    }

    public List<Orders> getOrdersByCatererIdAndStatusPending(Long catererId) {
        return orderRepository.findByCatererIdAndStatus(catererId, "Pending");
    }
}

