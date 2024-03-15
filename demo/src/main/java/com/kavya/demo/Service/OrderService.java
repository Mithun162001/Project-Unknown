package com.kavya.demo.Service;

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
     public Orders getOrdersByCatererId(Long catererId) {
        return orderRepository.findFirstByCatererId(catererId);
    }

    public void updateOrderStatusAndPrice(Long orderId, String status, double price) {
        Orders order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            order.setPrize(price);
            orderRepository.save(order);
        }
    }
    public Orders getOrderByCatererIdAndStatus(Long catererId, String status) {
        return orderRepository.findByCatererIdAndStatus(catererId, status);
    }
    
    
}

