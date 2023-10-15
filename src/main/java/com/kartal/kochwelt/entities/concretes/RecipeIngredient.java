package com.kartal.kochwelt.entities.concretes;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "recipes_ingredients")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeIngredient {
	@EmbeddedId
	RecipeIngredientKey id;
	
	@Column(name = "unit")
	private String unit;
	
	@Column(name = "amount")
	private double amount;
	
	@ManyToOne
	@MapsId("recipe_id")
	@JoinColumn(name = "recipe_id")
	private Recipe recipe;
	
	@ManyToOne
	@MapsId("ingredient_id")
	@JoinColumn(name = "ingredient_id")
	private Ingredient ingredient;
}