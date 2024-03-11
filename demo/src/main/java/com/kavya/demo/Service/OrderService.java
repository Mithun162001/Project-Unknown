package com.kavya.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kavya.demo.model.Orders;
import com.kavya.demo.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    // This constant defines the cost per person
    private static final double COST_PER_PERSON = 100.0;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Saves an order to the database with the total cost calculated as 
     * the number of people multiplied by 100.
     *
     * @param order the order to save
     */
    public void saveOrder(Orders order) {
        // Calculate the total cost based on the number of people
        double totalCost = calculateTotalCost(order.getNumberOfPeople());
        order.setPrice(totalCost);

        // Save the order with the calculated cost
        orderRepository.save(order);
    }

    /**
     * Calculates the total cost of an order based on the number of people.
     * The cost per person is fixed at 100.
     *
     * @param numberOfPeople the number of people for the order
     * @return the total cost
     */
    private double calculateTotalCost(int numberOfPeople) {
        return numberOfPeople * COST_PER_PERSON;
    }
}
