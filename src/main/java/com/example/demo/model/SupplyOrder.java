// SupplyOrder.java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class SupplyOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String specificationName;

    private String orderType;

    private String supplyType;

    private String orderDate;

    private String deliveryDate;

    private boolean active; // 🔥 вот это поле нужно

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    // --- Геттеры и сеттеры ---

    public Long getId() {
        return id;
    }

    public String getSpecificationName() {
        return specificationName;
    }

    public String getOrderType() {
        return orderType;
    }

    public String getSupplyType() {
        return supplyType;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public boolean isActive() {
        return active;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSpecificationName(String specificationName) {
        this.specificationName = specificationName;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public void setSupplyType(String supplyType) {
        this.supplyType = supplyType;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
}
