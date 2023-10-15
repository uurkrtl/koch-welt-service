package com.kartal.kochwelt.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kartal.kochwelt.business.abstracts.RecipeService;
import com.kartal.kochwelt.business.requests.RecipeCreateRequest;
import com.kartal.kochwelt.business.requests.RecipeUpdateRequest;
import com.kartal.kochwelt.business.responses.RecipesGetAllResponse;
import com.kartal.kochwelt.business.responses.RecipesGetByIdResponse;
import com.kartal.kochwelt.business.rules.RecipeBusinessRules;
import com.kartal.kochwelt.core.utilities.mappers.ModelMapperService;
import com.kartal.kochwelt.dataAccess.abstracts.CategoryRepository;
import com.kartal.kochwelt.dataAccess.abstracts.RecipeRepository;
import com.kartal.kochwelt.entities.concretes.Recipe;
import com.kartal.kochwelt.entities.concretes.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeManager implements RecipeService {
	private RecipeRepository recipeRepository;
	private CategoryRepository categoryRepository;
	private ModelMapperService modelMapperService;
	private RecipeBusinessRules recipeBusinessRules;
	
	@Override
	public List<RecipesGetAllResponse> getAll(Optional<String> orderBy, Optional<Integer> limit, Optional<Long> categoryId) {
		this.recipeBusinessRules.checkIfOrderByNull(orderBy, limit);
		List<Recipe>recipes = this.recipeRepository.findAll();
		List<RecipesGetAllResponse> recipesGetAllRespons = recipes.stream().map(recipe->this.modelMapperService.forResponse().map(recipe, RecipesGetAllResponse.class)).collect(Collectors.toList());
		
		if(orderBy.isPresent() && limit.isEmpty()) {
			recipes = this.recipeRepository.findAll(Sort.by(Sort.Direction.DESC, orderBy.get()));
			recipesGetAllRespons = recipes.stream().map(recipe->this.modelMapperService.forResponse().map(recipe, RecipesGetAllResponse.class)).collect(Collectors.toList());
		}else if(orderBy.isPresent() && limit.isPresent()) {
			Page<Recipe> recipesPage = this.recipeRepository.findAll(PageRequest.of(0, limit.get(),Sort.by(Sort.Direction.DESC, orderBy.get())));
			recipesGetAllRespons = recipesPage.stream().map(recipe->this.modelMapperService.forResponse().map(recipe, RecipesGetAllResponse.class)).collect(Collectors.toList());
		}
		
		if(categoryId.isPresent()) {
			recipes = this.recipeRepository.findAllByCategoryId(categoryId.get(),Sort.by(Sort.Direction.DESC, "insertionDate"));
			recipesGetAllRespons = recipes.stream().map(recipe->this.modelMapperService.forResponse().map(recipe, RecipesGetAllResponse.class)).collect(Collectors.toList());
		}
		
		return recipesGetAllRespons;
	}
	
	@Override
	public RecipesGetByIdResponse getById(long id) {
		Recipe recipe = this.recipeRepository.findById(id).orElse(null);
		recipeBusinessRules.checkIfRecipeNull(recipe, id);
		RecipesGetByIdResponse recipesGetByIdResponse = this.modelMapperService.forResponse().map(recipe, RecipesGetByIdResponse.class);
		return recipesGetByIdResponse;
	}
	
	@Override
	public void add(RecipeCreateRequest recipeCreateRequest) {
		recipeBusinessRules.checkIfRecipeNameExists(recipeCreateRequest.getName());
		Recipe recipe = this.modelMapperService.forRequest().map(recipeCreateRequest, Recipe.class);
		recipe.setActive(true);
		recipe.setInsertionDate(LocalDate.now());
		this.recipeRepository.save(recipe);		
	}
	
	@Override
	public void update(RecipeUpdateRequest recipeUpdateRequest) {
		//recipeBusinessRules.checkIfRecipeNameExists(recipeUpdateRequest.getName());
		Recipe recipe = new Recipe();
		Recipe foundRecipe = this.recipeRepository.findById(recipeUpdateRequest.getId()).orElse(null);
		User foundUser = foundRecipe.getUser();
		recipe.setId(recipeUpdateRequest.getId());
		recipe.setName(recipeUpdateRequest.getName());
		recipe.setPreparation(recipeUpdateRequest.getPreparation());
		recipe.setPreparationTime(recipeUpdateRequest.getPreparationTime());
		recipe.setBakingTime(recipeUpdateRequest.getBakingTime());
		recipe.setInsertionDate(foundRecipe.getInsertionDate());
		recipe.setUpdateDate(LocalDate.now());
		recipe.setImageUrl(recipeUpdateRequest.getImageUrl());
		recipe.setRecipeWeek(recipeUpdateRequest.isRecipeWeek());
		recipe.setRecipeDay(recipeUpdateRequest.isRecipeDay());
		recipe.setReadCount(foundRecipe.getReadCount());
		recipe.setActive(recipeUpdateRequest.isActive());
		recipe.setUser(foundUser);
		recipe.setCategory(this.categoryRepository.findById(recipeUpdateRequest.getCategoryId()).orElse(null));
		this.recipeRepository.save(recipe);
	}
	
	@Override
	public void delete(long id) {
		Recipe recipe = this.recipeRepository.findById(id).orElse(null);
		recipe.setActive(false);
		this.recipeRepository.save(recipe);
	}
	
	@Override
	public RecipesGetByIdResponse readCounter(long id) {
		Recipe recipe = this.recipeRepository.findById(id).orElse(null);
		long newReadCount = recipe.getReadCount()+1;
		recipe.setReadCount(newReadCount);
		this.recipeRepository.save(recipe);
		RecipesGetByIdResponse recipeGetByIdResponse = this.modelMapperService.forResponse().map(recipe, RecipesGetByIdResponse.class);
		return recipeGetByIdResponse;
	}
}