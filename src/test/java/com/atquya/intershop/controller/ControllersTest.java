package com.atquya.intershop.controller;

import com.atquya.intershop.entities.CartItem;
import com.atquya.intershop.entities.Product;
import com.atquya.intershop.repository.CartItemsRepository;
import com.atquya.intershop.repository.ProductsRepository;
import com.atquya.intershop.service.ShoppingCartsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * The ControllerAllTests class contains unit tests for the Controller class.
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest(ControllerAll.class)
class ControllerAllTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ShoppingCartsService shoppingCartsService;

    @MockBean
    private ProductsRepository productsRepository;

    @MockBean
    private CartItemsRepository cartItemsRepository;

    /**
     * Tests the addProductToCart() method of the Controller class.
     *
     * @throws Exception if an exception occurs during the test.
     */
    @Test
    public void testAddProductToCart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/add")
                        .param("productID", "1")
                        .param("quantity", "2"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(shoppingCartsService, times(1)).addProductToCart(1L, 2);
    }

    /**
     * Tests the removeProductFromCart() method of the Controller class.
     *
     * @throws Exception if an exception occurs during the test.
     */
    @Test
    public void testRemoveProductFromCart() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/cart/remove/{cartItemId}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(shoppingCartsService, times(1)).removeProductFromCart(1L);
    }

    /**
     * Tests the changeQuantity() method of the Controller class.
     *
     * @throws Exception if an exception occurs during the test.
     */
    @Test
    public void testChangeQuantity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.put("/api/cart/quantity/{cartItemId}", 1L)
                        .param("newQuantity", "5"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(shoppingCartsService, times(1)).changeQuantity(1L, 5);
    }

    /**
     * Tests the getShoppingCart() method of the Controller class.
     *
     * @throws Exception if an exception occurs during the test.
     */
    @Test
    public void testGetShoppingCart() throws Exception {
        CartItem cartItem1 = new CartItem();
        cartItem1.setId(1L);
        cartItem1.setProduct(new Product());
        cartItem1.setQuantity(2);

        CartItem cartItem2 = new CartItem();
        cartItem2.setId(2L);
        cartItem2.setProduct(new Product());
        cartItem2.setQuantity(3);

        List<CartItem> cartItems = Arrays.asList(cartItem1, cartItem2);

        when(shoppingCartsService.getShoppingCart()).thenReturn(cartItems);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cart/items"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].quantity").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].quantity").value(3));

        verify(shoppingCartsService, times(1)).getShoppingCart();
    }

    /**
     * Tests the addCartItem() method of the Controller class.
     *
     * @throws Exception if an exception occurs during the test.
     */
    @Test
    public void testAddCartItem() throws Exception {
        CartItem cartItem = new CartItem();
        cartItem.setId(1L);
        cartItem.setProduct(new Product());
        cartItem.setQuantity(2);

        when(cartItemsRepository.save(any(CartItem.class))).thenReturn(cartItem);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/cart/items/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"product\": {}, \"quantity\": 2}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity").value(2));

        verify(cartItemsRepository, times(1)).save(any(CartItem.class));
    }

    /**
     * Tests the addProduct() method of the Controller class.
     *
     * @throws Exception if an exception occurs during the test.
     */
    @Test
    public void testAddProduct() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(19.99);

        when(productsRepository.save(any(Product.class))).thenReturn(product);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/products/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"Product 1\", \"price\": 19.99}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(19.99));

        verify(productsRepository, times(1)).save(any(Product.class));
    }

    /**
     * Tests the getAllProducts() method of the Controller class.
     *
     * @throws Exception if an exception occurs during the test.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");
        product1.setPrice(19.99);

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");
        product2.setPrice(29.99);

        List<Product> products = Arrays.asList(product1, product2);

        when(productsRepository.findAll()).thenReturn(products);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Product 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].price").value(19.99))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value("Product 2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].price").value(29.99));

        verify(productsRepository, times(1)).findAll();
    }

    /**
     * Tests the getProductById() method of the Controller class.
     *
     * @throws Exception if an exception occurs during the test.
     */
    @Test
    public void testGetProductById() throws Exception {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setPrice(19.99);

        when(productsRepository.findById(1L)).thenReturn(Optional.of(product));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Product 1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(19.99));

        verify(productsRepository, times(1)).findById(1L);
    }

    /**
     * Tests the updateProduct() method of the Controller class.
     *
     * @throws Exception if an exception occurs during the test.
     */
    @Test
    public void testUpdateProduct() throws Exception {
        Product existingProduct = new Product();
        existingProduct.setId(1L);
        existingProduct.setName("Product 1");
        existingProduct.setPrice(19.99);

        Product updatedProduct = new Product();
        updatedProduct.setId(1L);
        updatedProduct.setName("Updated Product");
        updatedProduct.setPrice(24.99);

        when(productsRepository.findById(1L)).thenReturn(Optional.of(existingProduct));
        when(productsRepository.save(any(Product.class))).thenReturn(updatedProduct);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/products/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"name\": \"Updated Product\", \"price\": 24.99}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Product"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(24.99));

        verify(productsRepository, times(1)).findById(1L);
        verify(productsRepository, times(1)).save(any(Product.class));
    }

    /**
     * Tests the deleteProduct() method of the Controller class.
     *
     * @throws Exception if an exception occurs during the test.
     */
    @Test
    public void testDeleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/products/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(productsRepository, times(1)).deleteById(1L);
    }
}
