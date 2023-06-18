package com.atquya.intershop.service;

import com.atquya.intershop.entities.CartItem;
import com.atquya.intershop.entities.Product;
import com.atquya.intershop.repository.CartItemsRepository;
import com.atquya.intershop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * The ShoppingCartsService class provides methods for managing a shopping cart.
 * It allows adding products to the cart, removing products from the cart,
 * changing the quantity of items in the cart, and retrieving the contents of the cart.
 */
@Service
public class ShoppingCartsService {
    private final CartItemsRepository cartItemsRepository;
    private final ProductsRepository productsRepository;

    /**
     * Constructs a ShoppingCartService with the specified repositories.
     *
     * @param cartItemsRepository the repository for managing CartItem entities
     * @param productsRepository the repository for managing Product entities
     */
    @Autowired
    public ShoppingCartsService(CartItemsRepository cartItemsRepository, ProductsRepository productsRepository) {
        this.cartItemsRepository = cartItemsRepository;
        this.productsRepository = productsRepository;
    }

    /**
     * Adds a product to the shopping cart with the specified quantity.
     * If the product is already in the cart, the quantity is updated.
     *
     * @param productId the ID of the product to add
     * @param quantity the quantity of the product to add
     * @throws IllegalArgumentException if the product is not found with the specified ID
     */
    @Transactional
    public void addProductToCart(Long productId, int quantity) {
        Optional<Product> productOptional = productsRepository.findById(productId);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            CartItem cartItem = cartItemsRepository.findByProduct(new Product());

            if (cartItem != null) {
                cartItem.setQuantity(cartItem.getQuantity() + quantity);
            } else {
                cartItem = new CartItem(product, quantity);
            }

            cartItemsRepository.save(cartItem);
        } else {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }
    }

    /**
     * Removes a product from the shopping cart with the specified cart item ID.
     *
     * @param cartItemId the ID of the cart item to remove
     * @throws IllegalArgumentException if the cart item is not found with the specified ID
     */
    @Transactional
    public void removeProductFromCart(Long cartItemId) {
        Optional<CartItem> cartItemOptional = cartItemsRepository.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItemsRepository.delete(cartItem);
        } else {
            throw new IllegalArgumentException("Cart item not found with ID: " + cartItemId);
        }
    }

    /**
     * Changes the quantity of a product in the shopping cart with the specified cart item ID.
     *
     * @param cartItemId the ID of the cart item to update
     * @param newQuantity the new quantity of the product
     * @throws IllegalArgumentException if the cart item is not found with the specified ID
     */
    @Transactional
    public void changeQuantity(Long cartItemId, int newQuantity) {
        Optional<CartItem> cartItemOptional = cartItemsRepository.findById(cartItemId);
        if (cartItemOptional.isPresent()) {
            CartItem cartItem = cartItemOptional.get();
            cartItem.setQuantity(newQuantity);
            cartItemsRepository.save(cartItem);
        } else {
            throw new IllegalArgumentException("Cart item not found with ID: " + cartItemId);
        }
    }

    /**
     * Retrieves the contents of the shopping cart.
     *
     * @return a list of CartItem objects representing the items in the cart
     */
    @Transactional
    public List<CartItem> getShoppingCart() {
        return cartItemsRepository.findAll();
    }
}
