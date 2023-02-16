package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.entity.Products;

public interface RestRepository extends JpaRepository<Products, Integer> {

}
