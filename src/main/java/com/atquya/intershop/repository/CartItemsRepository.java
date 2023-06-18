package com.atquya.intershop.repository;


import com.atquya.intershop.entities.CartItem;
import com.atquya.intershop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CartItemsRepository extends JpaRepository<CartItem, Long> {
    CartItem findByProduct(Product product);
}
