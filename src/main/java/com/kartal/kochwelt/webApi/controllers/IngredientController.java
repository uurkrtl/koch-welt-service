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

import com.kartal.kochwelt.business.abstracts.IngredientService;
import com.kartal.kochwelt.business.requests.IngredientCreateRequest;
import com.kartal.kochwelt.business.requests.IngredientUpdateRequest;
import com.kartal.kochwelt.business.responses.IngredientGetByIdResponse;
import com.kartal.kochwelt.business.responses.IngredientsGetAllResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/ingredients")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class IngredientController {
	private IngredientService ingredientService;
	
	@GetMapping
	List<IngredientsGetAllResponse> getAll(){
		return this.ingredientService.getAll();
	}
	
	@GetMapping("/{id}")
	IngredientGetByIdResponse getById(@RequestParam long id) {
		return this.ingredientService.getById(id);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody IngredientCreateRequest ingredientCreateRequest) {
		this.ingredientService.add(ingredientCreateRequest);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody IngredientUpdateRequest ingredientUpdateRequest) {
		this.ingredientService.update(ingredientUpdateRequest);
	}
}