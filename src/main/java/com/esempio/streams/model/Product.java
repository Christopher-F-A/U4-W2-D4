package com.esempio.streams.model;

public class Product {
    private Long id;
    private String name;
    private String category;
    private Double price;

    public Product(Long id, String name, String category, Double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
}

