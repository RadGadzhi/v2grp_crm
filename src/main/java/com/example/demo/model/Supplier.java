package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "suppliers")
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supplierId;

    private String name;
    private String contactName;
    private String phone;
    private String email;

    // геттеры и сеттеры
    public Long getSupplierId() { return supplierId; }

    public void setSupplierId(Long supplierId) { this.supplierId = supplierId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getContactName() { return contactName; }

    public void setContactName(String contactName) { this.contactName = contactName; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }
}
