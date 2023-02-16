package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.spring.entity.Products;
import com.spring.repository.SortingPagingRepository;

@Service
public class SortServices {

	@Autowired
	SortingPagingRepository sortrepo;

	public Iterable<Products> sortByField(String field, String choice) {

		Sort sortField;

		if (choice.equalsIgnoreCase("ASC")) {

			sortField = Sort.by(field).ascending();
		} else {

			sortField = Sort.by(field).descending();
		}

		return sortrepo.findAll(sortField);
	}

	public Iterable<Products> sortByGroup(String field1, String field2) {

		Sort sortField1 = Sort.by(field1);
		Sort sortField2 = Sort.by(field2);

		return sortrepo.findAll(sortField1.and(sortField2));
	}

	public Page<Products> getPage(int pageNumber, int pageSize) {

		Pageable pages = PageRequest.of(pageNumber, pageSize);

		return sortrepo.findAll(pages);
	}

	public Page<Products> getSortedPage(int pageNumber, int pageSize,String field, String choice) {

		Pageable pages;
		
		if(choice.equalsIgnoreCase("ASC")) {
			
			
			pages = PageRequest.of(pageNumber, pageSize, Sort.by(field));
		}
		else {
			
			pages = PageRequest.of(pageNumber, pageSize, Direction.DESC, field);
		}

		return sortrepo.findAll(pages);
	}
}
