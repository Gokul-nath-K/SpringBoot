package com.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.entity.Products;
import com.spring.service.RestServices;
import com.spring.service.SortServices;

@RestController
public class controller {

	@Autowired
	RestServices restservice;

	@Autowired
	SortServices sortservice;

	@PostMapping("/postRecord")
	public String post(@RequestBody Products prod) {

		restservice.postRecord(prod);
		return "Your record is saved";

	}

	@GetMapping("/getAllRecords")
	public List<Products> get() {

		return restservice.getAllRecords();

	}

	@GetMapping("/{id}/getById")
	public Products get(@PathVariable("id") int id) {

		return restservice.getRecordsById(id);

	}

	@PutMapping("/{id}/update")
	public String put(@RequestBody Products prod, @PathVariable("id") int id) {

		restservice.updateRecordById(id, prod);
		return "ID : " + id + " is updated successfully";
	}

	@DeleteMapping("/deleteAllRecors")
	public String delete() {

		restservice.deleteAllRecords();
		return "All records are deleted";
	}

	@DeleteMapping("/{id}/deleteById")
	public String delete(@PathVariable("id") int id) {

		restservice.deleteById(id);
		return "id : " + id + " is deleted";
	}

	@GetMapping("/sortbyfield/{field}")
	public Iterable<Products> sortByField(@PathVariable String field, @RequestParam(defaultValue = "asc", required = false) String choice) {

		return sortservice.sortByField(field, choice);
	}

	@GetMapping("/sortbyfield/{field1}/{field2}")
	public Iterable<Products> sortByGroup(@PathVariable String field1, @PathVariable String field2) {

		return sortservice.sortByGroup(field1, field2);
	}
	
	@GetMapping("/GetPage")
	public Page<Products> getPages(@RequestParam(defaultValue = "0", required = false) int pageNumber, @RequestParam(defaultValue = "5", required = false) int pageSize) {
		
		return sortservice.getPage(pageNumber, pageSize);
	}
	
	@GetMapping("/GetSortedPage")
	public Page<Products> getSortedPages(@RequestParam(defaultValue = "0", required = false) int pageNumber, @RequestParam(defaultValue = "5", required = false) int pageSize, @RequestParam(defaultValue = "asc", required = false) String choice, @RequestParam(defaultValue = "id", required = false) String field) {
		
		return sortservice.getSortedPage(pageNumber, pageSize, field, choice);
	}
}
