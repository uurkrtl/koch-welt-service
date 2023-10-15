package com.kartal.kochwelt.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.kartal.kochwelt.business.requests.RecipeCreateRequest;
import com.kartal.kochwelt.business.requests.RecipeUpdateRequest;
import com.kartal.kochwelt.business.responses.RecipesGetAllResponse;
import com.kartal.kochwelt.business.responses.RecipesGetByIdResponse;

public interface RecipeService {
	List<RecipesGetAllResponse> getAll(Optional<String> orderBy, Optional<Integer> limit, Optional<Long> categoryId);
	RecipesGetByIdResponse getById(long id);
	public void add(RecipeCreateRequest recipeCreateRequest);
	public void update(RecipeUpdateRequest recipeUpdateRequest);
	public void delete (long id);
	RecipesGetByIdResponse readCounter(long id);
}