package com.kartal.kochwelt.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kartal.kochwelt.business.abstracts.RecipeIngredientService;
import com.kartal.kochwelt.business.requests.RecipeIngredientCreateRequest;
import com.kartal.kochwelt.business.requests.RecipeIngredientUpdateRequest;
import com.kartal.kochwelt.business.responses.RecipeIngredientsGetByRecipeIdResponse;
import com.kartal.kochwelt.core.utilities.mappers.ModelMapperService;
import com.kartal.kochwelt.dataAccess.abstracts.IngredientRepository;
import com.kartal.kochwelt.dataAccess.abstracts.RecipeIngredientRepository;
import com.kartal.kochwelt.entities.concretes.RecipeIngredient;
import com.kartal.kochwelt.entities.concretes.RecipeIngredientKey;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeIngredientManager implements RecipeIngredientService {
	private RecipeIngredientRepository recipeIngredientRepository;
	private IngredientRepository ingredientRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<RecipeIngredientsGetByRecipeIdResponse> getByRecipeId(long recipeId) {
		List<RecipeIngredient> recipeIngredients = this.recipeIngredientRepository.findByRecipeId(recipeId);
		List<RecipeIngredientsGetByRecipeIdResponse> recipeIngredientsGetByRecipeIdResponses = recipeIngredients.stream().map(recipeIngredient->this.modelMapperService.forResponse().map(recipeIngredient, RecipeIngredientsGetByRecipeIdResponse.class)).collect(Collectors.toList());
		return recipeIngredientsGetByRecipeIdResponses;
	}

	@Override
	public void add(RecipeIngredientCreateRequest recipeIngredientCreateRequest) {
		RecipeIngredient recipeIngredient = this.modelMapperService.forRequest().map(recipeIngredientCreateRequest, RecipeIngredient.class);
		this.recipeIngredientRepository.save(recipeIngredient);
	}

	@Override
	public void update(RecipeIngredientUpdateRequest recipeIngredientUpdateRequest) {
		RecipeIngredient recipeIngredient = new RecipeIngredient();
		recipeIngredient.setId(recipeIngredientUpdateRequest.getId());
		recipeIngredient.setUnit(recipeIngredientUpdateRequest.getUnit());
		recipeIngredient.setAmount(recipeIngredientUpdateRequest.getAmount());
		recipeIngredient.setIngredient(this.ingredientRepository.findById(recipeIngredientUpdateRequest.getIngredientId()).orElse(null));
		this.recipeIngredientRepository.save(recipeIngredient);
	}

	@Override
	public void delete(RecipeIngredientKey id) {
		this.recipeIngredientRepository.deleteById(id);
	}
}