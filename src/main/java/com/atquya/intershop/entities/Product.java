package com.atquya.intershop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The Product class represents a product in the shopping.
 * It contains information such as the product's name and price.
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;

    /**
     * Constructs an empty Product object.
     */
    public Product() {
    }

    /**
     * Constructs a Product object with the specified name and price.
     *
     * @param name  the name of the product
     * @param price the price of the product
     */
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Retrieves the id of the product.
     *
     * @return the id of the product
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the product.
     *
     * @param id the id of the product
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the name of the product.
     *
     * @return the name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return the price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of the product.
     *
     * @param price the price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns a string representation of the Product object.
     *
     * @return a string representation of the Product object
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
