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

    public boolean isValidCaterer(String email, String password) {
        Caterer caterer = catererRepository.findByEmail(email);
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
