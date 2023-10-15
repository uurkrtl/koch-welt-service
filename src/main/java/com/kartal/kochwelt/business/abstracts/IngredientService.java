package com.kartal.kochwelt.business.abstracts;

import java.util.List;

import com.kartal.kochwelt.business.requests.IngredientCreateRequest;
import com.kartal.kochwelt.business.requests.IngredientUpdateRequest;
import com.kartal.kochwelt.business.responses.IngredientGetByIdResponse;
import com.kartal.kochwelt.business.responses.IngredientsGetAllResponse;

public interface IngredientService {
	List<IngredientsGetAllResponse> getAll();
	IngredientGetByIdResponse getById(long id);
	public void add(IngredientCreateRequest ingredientCreateRequest);
	public void update(IngredientUpdateRequest ingredientUpdateRequest);
}