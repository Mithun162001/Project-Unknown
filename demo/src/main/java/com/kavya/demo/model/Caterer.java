package com.kavya.demo.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Caterer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String FullName;
    @Column(unique = true)
    private String Email;
    private String Address;
    private String Phonenumber;
    private String Password;

    @OneToMany(mappedBy = "caterer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    // @OneToMany(mappedBy = "caterer")
    // private List<AccountDetails> paymentDetails;

    // @OneToMany(mappedBy = "caterer", cascade = CascadeType.ALL)
    // private List<Review> reviews = new ArrayList<>();
    
    public Caterer() {}

    public Caterer(Long id, String fullName, String email, String address, String phonenumber, String password) {
        this.id = id;
        FullName = fullName;
        Email = email;
        Address = address;
        Phonenumber = phonenumber;
        Password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Override
    public String toString() {
        return "Caterer [id=" + id + ", FullName=" + FullName + ", Email=" + Email + ", Address=" + Address
                + ", Phonenumber=" + Phonenumber + ", Password=" + Password + "]";
    }

    
}
