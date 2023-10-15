package com.kartal.kochwelt.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kartal.kochwelt.business.abstracts.CategoryService;
import com.kartal.kochwelt.business.requests.CategoryCreateRequest;
import com.kartal.kochwelt.business.requests.CategoryUpdateRequest;
import com.kartal.kochwelt.business.responses.CategoriesGetAllResponse;
import com.kartal.kochwelt.business.responses.CategoryGetByIdResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
	private CategoryService categoryService;
	
	@GetMapping
	List<CategoriesGetAllResponse> getAll(){
		return this.categoryService.getAll();
	}
	
	@GetMapping("/{id}")
	CategoryGetByIdResponse getById(@RequestParam long id) {
		return this.categoryService.getById(id);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody CategoryCreateRequest categoryCreateRequest) {
		this.categoryService.add(categoryCreateRequest);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody CategoryUpdateRequest categoryUpdateRequest) {
		this.categoryService.update(categoryUpdateRequest);
	}
	
	@PutMapping("/delete")
	public void delete(@RequestParam long id) {
		this.categoryService.delete(id);
	}
}