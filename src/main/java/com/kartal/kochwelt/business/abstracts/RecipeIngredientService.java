package com.kartal.kochwelt.business.abstracts;

import java.util.List;

import com.kartal.kochwelt.business.requests.RecipeIngredientCreateRequest;
import com.kartal.kochwelt.business.requests.RecipeIngredientUpdateRequest;
import com.kartal.kochwelt.business.responses.RecipeIngredientsGetByRecipeIdResponse;
import com.kartal.kochwelt.entities.concretes.RecipeIngredientKey;

public interface RecipeIngredientService {
	List<RecipeIngredientsGetByRecipeIdResponse> getByRecipeId(long id);
	public void add(RecipeIngredientCreateRequest recipeIngredientCreateRequest);
	public void update(RecipeIngredientUpdateRequest recipeIngredientUpdateRequest);
	public void delete(RecipeIngredientKey id);
}