package com.kartal.kochwelt.business.rules;

import org.springframework.stereotype.Service;

import com.kartal.kochwelt.core.exception.NotFoundException;
import com.kartal.kochwelt.dataAccess.abstracts.IngredientRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class IngredientBusinessRules {
	private IngredientRepository ingredientRepository;
	
	public void checkIfIngredientNameExists(String name) {
		if(this.ingredientRepository.existsByName(name)) {
			throw new NotFoundException("Name der Zutat existiert bereits");
		}
	}
}