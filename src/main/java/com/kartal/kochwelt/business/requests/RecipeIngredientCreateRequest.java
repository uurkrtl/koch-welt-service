package com.kartal.kochwelt.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientCreateRequest {
	private String unit;
	private double amount;
	private long recipeId;
	private long ingredientId;
}