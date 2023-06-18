package com.atquya.intershop.repository;


import com.atquya.intershop.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductsRepository extends JpaRepository<Product, Long> {

}
