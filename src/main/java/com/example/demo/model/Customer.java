package com.example.demo.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id") // Ensure it matches your DB column
    private Long id;

    private String name;
    private String contactName;
    private String email;
    private String phone;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<SupplyOrder> orders;

    // --- Constructors ---

    public Customer() {
    }

    public Customer(String name, String contactName, String email, String phone) {
        this.name = name;
        this.contactName = contactName;
        this.email = email;
        this.phone = phone;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<SupplyOrder> getOrders() {
        return orders;
    }

    public void setOrders(List<SupplyOrder> orders) {
        this.orders = orders;
    }
}
