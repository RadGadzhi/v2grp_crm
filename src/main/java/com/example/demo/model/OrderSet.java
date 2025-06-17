package com.example.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class OrderSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private SupplyOrder order;

    @OneToMany(mappedBy = "orderSet", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SetProduct> products;

    // --- Геттеры и сеттеры ---

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SupplyOrder getOrder() {
        return order;
    }

    public List<SetProduct> getProducts() {
        return products;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(SupplyOrder order) {
        this.order = order;
    }

    public void setProducts(List<SetProduct> products) {
        this.products = products;
    }
}
