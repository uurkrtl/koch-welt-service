package com.kartal.kochwelt.entities.concretes;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredientKey implements Serializable {
	@Column(name = "recipe_id")
	private Long recipeId;
	
	@Column(name = "ingredient_id")
	private Long ingredientId;
}