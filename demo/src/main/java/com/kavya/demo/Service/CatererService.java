package com.kavya.demo.Service;

import com.kavya.demo.model.Caterer;
import com.kavya.demo.repository.CatererRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CatererService {

    @Autowired
    private CatererRepository catererRepository;

    // Generate a token for authentication
    public String generateToken() {
        return UUID.randomUUID().toString();
    }

    public boolean isValidCaterer(String email, String password) {
        Caterer caterer = catererRepository.findByEmail(email);
        return caterer != null && caterer.getPassword().equals(password);
    }

    // Generate token for a caterer and return it
    public String generateToken(String email) {
        Caterer caterer = catererRepository.findByEmail(email);
        if (caterer != null) {
            String token = generateToken();
            caterer.setToken(token); // Set token in caterer entity
            catererRepository.save(caterer); // Update caterer entity in the database
            return token;
        }
        return null;
    }

    public List<Caterer> getAllCaterers() {
        return catererRepository.findAll();
    }

    public Caterer getCatererById(Long id) {
        return catererRepository.findById(id).orElse(null);
    }

    public Caterer createCaterer(Caterer caterer) {
        return catererRepository.save(caterer);
    }

    public Caterer updateCaterer(Long id, Caterer caterer) {
        if (catererRepository.existsById(id)) {
            caterer.setId(id);
            return catererRepository.save(caterer);
        }
        return null;
    }

    public void deleteCaterer(Long id) {
        catererRepository.deleteById(id);
    }
}
