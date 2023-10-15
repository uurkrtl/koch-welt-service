package com.kartal.kochwelt.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kartal.kochwelt.business.abstracts.RecipeIngredientService;
import com.kartal.kochwelt.business.requests.RecipeIngredientCreateRequest;
import com.kartal.kochwelt.business.requests.RecipeIngredientUpdateRequest;
import com.kartal.kochwelt.business.responses.RecipeIngredientsGetByRecipeIdResponse;
import com.kartal.kochwelt.entities.concretes.RecipeIngredientKey;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/recipeIngredients")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RecipeIngredientController {
	private RecipeIngredientService recipeIngredientService;
	
	@GetMapping("/{recipeId}")
	List<RecipeIngredientsGetByRecipeIdResponse> getByRecipeId(@PathVariable long recipeId){
		return this.recipeIngredientService.getByRecipeId(recipeId);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody RecipeIngredientCreateRequest recipeIngredientCreateRequest) {
		this.recipeIngredientService.add(recipeIngredientCreateRequest);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody RecipeIngredientUpdateRequest recipeIngredientUpdateRequest) {
		this.recipeIngredientService.update(recipeIngredientUpdateRequest);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestParam RecipeIngredientKey id) {
		this.recipeIngredientService.delete(id);
	}
}