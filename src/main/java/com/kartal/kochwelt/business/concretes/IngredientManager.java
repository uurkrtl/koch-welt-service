package com.kartal.kochwelt.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kartal.kochwelt.business.abstracts.IngredientService;
import com.kartal.kochwelt.business.requests.IngredientCreateRequest;
import com.kartal.kochwelt.business.requests.IngredientUpdateRequest;
import com.kartal.kochwelt.business.responses.IngredientGetByIdResponse;
import com.kartal.kochwelt.business.responses.IngredientsGetAllResponse;
import com.kartal.kochwelt.business.rules.IngredientBusinessRules;
import com.kartal.kochwelt.core.utilities.mappers.ModelMapperService;
import com.kartal.kochwelt.dataAccess.abstracts.IngredientRepository;
import com.kartal.kochwelt.entities.concretes.Ingredient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IngredientManager implements IngredientService {
	private IngredientRepository ingredientRepository;
	private ModelMapperService modelMapperService;
	private IngredientBusinessRules ingredientBusinessRules;

	@Override
	public List<IngredientsGetAllResponse> getAll() {
		List<Ingredient> ingredients = this.ingredientRepository.findAll();
		List<IngredientsGetAllResponse> ingredientsGetAllRespons = ingredients.stream().map(ingredient->this.modelMapperService.forResponse().map(ingredient, IngredientsGetAllResponse.class)).collect(Collectors.toList());
		return ingredientsGetAllRespons;
	}

	@Override
	public IngredientGetByIdResponse getById(long id) {
		Ingredient ingredient = this.ingredientRepository.findById(id).orElse(null);
		IngredientGetByIdResponse ingredientGetByIdResponse = this.modelMapperService.forResponse().map(ingredient, IngredientGetByIdResponse.class);
		return ingredientGetByIdResponse;
	}

	@Override
	public void add(IngredientCreateRequest ingredientCreateRequest) {
		ingredientBusinessRules.checkIfIngredientNameExists(ingredientCreateRequest.getName());
		Ingredient ingredient = this.modelMapperService.forRequest().map(ingredientCreateRequest, Ingredient.class);
		this.ingredientRepository.save(ingredient);
	}

	@Override
	public void update(IngredientUpdateRequest ingredientUpdateRequest) {
		ingredientBusinessRules.checkIfIngredientNameExists(ingredientUpdateRequest.getName());
		Ingredient ingredient = new Ingredient();
		ingredient.setId(ingredientUpdateRequest.getId());
		ingredient.setName(ingredientUpdateRequest.getName());
		this.ingredientRepository.save(ingredient);
	}
}