package com.khan.springcloud.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.khan.springcloud.model.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {

}
