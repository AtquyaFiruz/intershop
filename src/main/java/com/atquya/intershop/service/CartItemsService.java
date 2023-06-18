package com.atquya.intershop.service;

import com.atquya.intershop.entities.CartItem;
import com.atquya.intershop.entities.Product;
import org.springframework.stereotype.Service;

/**
 * The CartItemService class provides methods for converting entities to DTOs.
 * It is responsible for converting CartItem and Product objects to their respective DTO counterparts.
 */
@Service
public class CartItemsService {

    /**
     * Converts a CartItem object to a CartItem DTO object.
     *
     * @param cartItem the CartItem object to convert
     * @return the converted CartItem DTO object
     */
    public CartItem convertToDto (CartItem cartItem) {
        CartItem DTOcartItem = new CartItem();
        DTOcartItem.setId(cartItem.getId());
        DTOcartItem.setProduct(convertToDto(cartItem.getProduct()));
        DTOcartItem.setQuantity(cartItem.getQuantity());
        return DTOcartItem;
    }

    /**
     * Converts a Product object to a Product DTO object.
     *
     * @param product the Product object to convert
     * @return the converted Product DTO object
     */
    public Product convertToDto(Product product) {
        Product DTOproduct = new Product();
        DTOproduct.setId(product.getId());
        DTOproduct.setName(product.getName());
        DTOproduct.setPrice(product.getPrice());
        return DTOproduct;
    }
}
