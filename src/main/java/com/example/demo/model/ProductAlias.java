package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product_aliases")
public class ProductAlias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String alias;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    // Геттеры и сеттеры

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getAlias() { return alias; }

    public void setAlias(String alias) { this.alias = alias; }

    public Product getProduct() { return product; }

    public void setProduct(Product product) { this.product = product; }
}
