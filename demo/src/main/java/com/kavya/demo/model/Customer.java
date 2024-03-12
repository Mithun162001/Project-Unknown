package com.kavya.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique = true)
    private String email;
    private String Address;
    private String Phonenumber;
    private String Password;

    // @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    // private List<Order> orders = new ArrayList<>();

    // @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    // private List<Review> reviews = new ArrayList<>();

    

    public Customer() {}

    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((fullName == null) ? 0 : fullName.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((Address == null) ? 0 : Address.hashCode());
        result = prime * result + ((Phonenumber == null) ? 0 : Phonenumber.hashCode());
        result = prime * result + ((Password == null) ? 0 : Password.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Customer other = (Customer) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (fullName == null) {
            if (other.fullName != null)
                return false;
        } else if (!fullName.equals(other.fullName))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (Address == null) {
            if (other.Address != null)
                return false;
        } else if (!Address.equals(other.Address))
            return false;
        if (Phonenumber == null) {
            if (other.Phonenumber != null)
                return false;
        } else if (!Phonenumber.equals(other.Phonenumber))
            return false;
        if (Password == null) {
            if (other.Password != null)
                return false;
        } else if (!Password.equals(other.Password))
            return false;
        return true;
    }


    @Override
    public String toString() {
        return "Customer [id=" + id + ", FullName=" + fullName + ", Email=" + email + ", Address=" + Address
                + ", Phonenumber=" + Phonenumber + ", Password=" + Password + "]";
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


    public Customer(Long id, String fullName, String email, String address, String phonenumber, String password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        Address = address;
        Phonenumber = phonenumber;
        Password = password;
    }
    
    
}
