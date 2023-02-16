package com.spring.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.entity.Products;
import com.spring.repository.RestRepository;
import com.spring.repository.SortingPagingRepository;

@Service
public class RestServices {

	@Autowired
	RestRepository restRepo;
	
	public void postRecord(Products prod) {
		
		restRepo.save(prod);
	}
	
	public List<Products> getAllRecords() {
		
		List<Products> list = new ArrayList<>();
		restRepo.findAll().forEach(item -> list.add(item));
		
		return list;
	}

	public Products getRecordsById(int id) {
	
		return restRepo.findById(id).get();
	}
	
	public String updateRecordById(int id, Products prod) {
		
		Products updateStudent = restRepo.findById(id).get();

		 if(updateStudent == null) {
			 
			 return "Student not found with Id : " + id;
		 }
		 else {
			 
			 updateStudent.setName(prod.getName());
			 updateStudent.setQuantity(prod.getQuantity());
			 updateStudent.setType(prod.getType());
		 }
		 
		 restRepo.save(updateStudent);
		 
		 return "Id : " + id + " is updated";
	}
	
	public void deleteAllRecords() {
		
		restRepo.deleteAll();
	}
	
	public void deleteById(int id) {
		
		restRepo.deleteById(id);
	}
	
}
