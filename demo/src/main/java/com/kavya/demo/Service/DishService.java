package com.kavya.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kavya.demo.model.Dish;
import com.kavya.demo.repository.DishRepository;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    public void saveDish(Dish dish) {
        dishRepository.save(dish);
    }
}
