package com.kartal.kochwelt.webApi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kartal.kochwelt.business.abstracts.RecipeService;
import com.kartal.kochwelt.business.requests.RecipeCreateRequest;
import com.kartal.kochwelt.business.requests.RecipeUpdateRequest;
import com.kartal.kochwelt.business.responses.RecipesGetAllResponse;
import com.kartal.kochwelt.business.responses.RecipesGetByIdResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/recipes")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeController {
	private RecipeService recipeService;
	
	@GetMapping()
	List<RecipesGetAllResponse> getAll(@RequestParam Optional<String> orderBy, Optional<Integer> limit, Optional<Long> categoryId){
		return this.recipeService.getAll(orderBy, limit, categoryId);
	}
	
	@GetMapping("/{id}")
	RecipesGetByIdResponse getById(@PathVariable long id) {
		return this.recipeService.getById(id);
	}
	
	@PostMapping("/add")
	public void add(@Valid @RequestBody RecipeCreateRequest recipeCreateRequest) {
		this.recipeService.add(recipeCreateRequest);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody RecipeUpdateRequest recipeUpdateRequest) {
		this.recipeService.update(recipeUpdateRequest);
	}
	
	@PutMapping("/delete")
	public void delete(@RequestParam long id) {
		this.recipeService.delete(id);
	}
	
	@PutMapping("readCounter")
	public RecipesGetByIdResponse readCounter( @RequestParam long id) {
		return this.recipeService.readCounter(id);
	}
}