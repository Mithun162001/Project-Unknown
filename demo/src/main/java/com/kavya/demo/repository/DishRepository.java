package com.kavya.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kavya.demo.model.Dish;



@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
}
