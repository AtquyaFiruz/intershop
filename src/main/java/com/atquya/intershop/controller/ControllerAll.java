package com.atquya.intershop.controller;

import com.atquya.intershop.entities.CartItem;
import com.atquya.intershop.entities.Product;
import com.atquya.intershop.repository.CartItemsRepository;
import com.atquya.intershop.repository.ProductsRepository;
import com.atquya.intershop.service.ShoppingCartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * The Controller class handles the API endpoints for managing all the operations.
 */
@RestController
@RequestMapping("/api")
public class ControllerAll {

    private final ProductsRepository productsRepository;
    private final CartItemsRepository cartItemsRepository;
    private final ShoppingCartsService shoppingCartsService;
    /**
     * Constructs a new Controller with the specified dependencies.
     *
     * @param shoppingCartsService   The shopping cart service.
     * @param productsRepository     The product repository.
     * @param cartItemsRepository    The cart item repository.
     */
    @Autowired
    public ControllerAll(ShoppingCartsService shoppingCartsService, ProductsRepository productsRepository, CartItemsRepository cartItemsRepository) {
        this.shoppingCartsService = shoppingCartsService;
        this.productsRepository = productsRepository;
        this.cartItemsRepository = cartItemsRepository;
    }

    // Shopping Cart Endpoints

    /**
     * Adds a product to the shopping cart.
     *
     * @param productID The ID of the product to add.
     * @param quantity  The quantity of the product to add.
     */
    @PostMapping("/cart/add")
    public void addProductToCart(@RequestParam Long productID, @RequestParam int quantity) {
        shoppingCartsService.addProductToCart(productID, quantity);
    }

    /**
     * Removes a product from the shopping cart.
     *
     * @param cartItemId The ID of the cart item to remove.
     */
    @DeleteMapping("/cart/remove/{cartItemId}")
    public void removeProductFromCart(@PathVariable Long cartItemId) {
        shoppingCartsService.removeProductFromCart(cartItemId);
    }

    /**
     * Changes the quantity of a product in the shopping cart.
     *
     * @param cartItemId   The ID of the cart item to update.
     * @param newQuantity  The new quantity of the product.
     */
    @PutMapping("/cart/quantity/{cartItemId}")
    public void changeQuantity(@PathVariable Long cartItemId, @RequestParam int newQuantity) {
        shoppingCartsService.changeQuantity(cartItemId, newQuantity);
    }

    /**
     * Retrieves the list of cart items in the shopping cart.
     *
     * @return The list of cart items.
     */
    @GetMapping("/cart/items")
    public List<CartItem> getShoppingCart() {
        return shoppingCartsService.getShoppingCart();
    }

    /**
     * Adds a cart item to the shopping cart.
     *
     * @param cartItem The cart item to add.
     * @return The added cart item.
     */
    @PostMapping("/cart/items/add")
    public CartItem addCartItem(@RequestBody CartItem cartItem) {
        return cartItemsRepository.save(cartItem);
    }

    // Product Endpoints

    /**
     * Adds a product to the product repository.
     *
     * @param product The product to add.
     * @return The added product.
     */
    @PostMapping("/products/add")
    public Product addProduct(@RequestBody Product product) {
        return productsRepository.save(product);
    }

    /**
     * Retrieves all products from the product repository.
     *
     * @return The list of products.
     */
    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productsRepository.findAll();
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product.
     * @return An optional containing the product if found, or empty if not found.
     */
    @GetMapping("/products/{id}")
    public Optional<Product> getProductById(@PathVariable Long id) {
        return productsRepository.findById(id);
    }

    /**
     * Updates a product in the product repository.
     *
     * @param id             The ID of the product to update.
     * @param updatedProduct The updated product data.
     * @return The updated product.
     * @throws RuntimeException if the product is not found with the specified ID.
     */
    @PutMapping("/products/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        Optional<Product> existingProduct = productsRepository.findById(id);
        if (existingProduct.isPresent()) {
            Product product = existingProduct.get();
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            return productsRepository.save(product);
        } else {
            throw new RuntimeException("Product not found with id: " + id);
        }
    }

    /**
     * Deletes a product from the product repository.
     *
     * @param id The ID of the product to delete.
     */
    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productsRepository.deleteById(id);
    }
}
