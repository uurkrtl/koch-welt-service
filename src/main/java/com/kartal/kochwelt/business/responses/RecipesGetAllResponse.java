package com.kartal.kochwelt.business.responses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipesGetAllResponse {
	private long id;
	private String name;
	private String preparation;
	private int preparationTime;
	private int bakingTime;
	private LocalDate insertionDate;
	private LocalDate updateDate;
	private long readCount;
	private String imageUrl;
	private boolean recipeWeek;
	private boolean recipeDay;
	private boolean isActive;
	private String userUserName;
	private String userFirstName;
	private String userLastName;
	private String userImageUrl;
	private long categoryId;
	private String categoryName;
	//private List<RecipeIngredient> ingredientName;
}