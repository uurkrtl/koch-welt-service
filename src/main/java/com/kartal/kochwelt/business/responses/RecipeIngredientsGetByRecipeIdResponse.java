package com.kartal.kochwelt.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientsGetByRecipeIdResponse {
	private String ingredientName;
	private String unit;
	private double amount;
}