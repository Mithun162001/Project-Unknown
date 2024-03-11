package com.kavya.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kavya.demo.model.Caterer;
import com.kavya.demo.repository.CatererRepository;


@Service
public class CatererService {
    @Autowired
    private  CatererRepository catererRepository;

    public List<Caterer> getAllCaterers() {
        return catererRepository.findAll();
    }

    public boolean isValidCaterer(String FullName, String password) {
        // Retrieve caterer from the database using the provided username
        Caterer caterer = catererRepository.findByFullName(FullName);
        // Check if a caterer with the provided username exists and if the password matches
        return caterer != null && caterer.getPassword().equals(password);
    }
    

    public Caterer getCatererById(Long id) {
        return catererRepository.findById(id).orElse(null);
    }

    public Caterer createCaterer(Caterer user) {
        return catererRepository.save(user);
    }

    @SuppressWarnings("null")
    public Caterer updateCaterer(Long id, Caterer user) {
        if (catererRepository.existsById(id)) {
            user.setId(id);
            return catererRepository.save(user);
        }
        return null;
    }

    @SuppressWarnings("null")
    public void deleteCaterer(Long id) {
        catererRepository.deleteById(id);
    }

    

}
