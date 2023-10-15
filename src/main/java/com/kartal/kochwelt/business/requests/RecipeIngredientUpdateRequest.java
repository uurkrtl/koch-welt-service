package com.kartal.kochwelt.business.requests;

import com.kartal.kochwelt.entities.concretes.RecipeIngredientKey;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientUpdateRequest {
	private RecipeIngredientKey id;
	private String unit;
	private double amount;
	private long recipeId;
	private long ingredientId;
}