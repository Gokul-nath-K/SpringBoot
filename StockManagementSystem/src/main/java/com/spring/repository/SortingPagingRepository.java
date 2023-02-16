package com.spring.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.spring.entity.Products;


public interface SortingPagingRepository extends PagingAndSortingRepository<Products, Integer> {

}
