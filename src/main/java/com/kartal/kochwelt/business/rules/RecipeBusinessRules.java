package com.kartal.kochwelt.business.rules;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kartal.kochwelt.core.exception.BadRequestException;
import com.kartal.kochwelt.core.exception.NotFoundException;
import com.kartal.kochwelt.dataAccess.abstracts.RecipeRepository;
import com.kartal.kochwelt.entities.concretes.Recipe;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RecipeBusinessRules {
	private RecipeRepository recipeRepository;
	
	public void checkIfRecipeNull(Recipe recipe, long id) {
		if(recipe==null) {
			throw new NotFoundException("Produkt nicht gefunden. Gesuchte ID: " + id);
		}
	}
	
	public void checkIfRecipeNameExists(String name) {
		if(this.recipeRepository.existsByName(name)) {
			throw new BadRequestException("Rezeptname existiert bereits");
		}
	}
	
	public void checkIfOrderByNull(Optional<String> orderBy, Optional<Integer> limit) {
		if(orderBy.isEmpty() && limit.isPresent()) {
			throw new BadRequestException("To be a limit, sort criteria must be entered.");
		}
	}
}