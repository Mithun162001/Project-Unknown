package com.kavya.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Caterer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String Address;
    private String Phonenumber;
    private String Password;

    // @OneToMany(mappedBy = "caterer", cascade = CascadeType.ALL)
    // private List<Order> orders = new ArrayList<>();

    // @OneToMany(mappedBy = "caterer")
    // private List<AccountDetails> paymentDetails;

    // @OneToMany(mappedBy = "caterer", cascade = CascadeType.ALL)
    // private List<Review> reviews = new ArrayList<>();
    
    public Caterer() {}

    public Caterer(Long id, String fullName, String email, String address, String phonenumber, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
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
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        return "Caterer [id=" + id + ", FullName=" + fullName + ", Email=" + email + ", Address=" + Address
                + ", Phonenumber=" + Phonenumber + ", Password=" + Password + "]";
    }

    
}
