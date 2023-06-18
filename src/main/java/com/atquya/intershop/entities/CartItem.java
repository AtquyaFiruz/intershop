package com.atquya.intershop.entities;

import jakarta.persistence.*;

/**
 * The CartItem class represents an item in a shopping cart.
 * It contains the information about the product and the quantity of that product in the cart.
 */
@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Product product;

    private int quantity;

    /**
     * Constructs an empty CartItem object.
     */
    public CartItem() {
    }

    /**
     * Constructs a CartItem object with the specified product and quantity.
     *
     * @param product  the product associated with the cart item
     * @param quantity the quantity of the product in the cart
     */
    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Retrieves the id of the cart item.
     *
     * @return the id of the cart item
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the id of the cart item.
     *
     * @param id the id of the cart item
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the product associated with the cart item.
     *
     * @return the product associated with the cart item
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product associated with the cart item.
     *
     * @param product the product associated with the cart item
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Retrieves the quantity of the product in the cart.
     *
     * @return the quantity of the product in the cart
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of the product in the cart.
     *
     * @param quantity the quantity of the product in the cart
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Returns a string representation of the CartItem object.
     *
     * @return a string representation of the CartItem object
     */
    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
