package com.kavya.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kavya.demo.model.Caterer;

@Repository
public interface CatererRepository extends JpaRepository<Caterer, Long>{
    Caterer findByFullName(String FullName);
}
