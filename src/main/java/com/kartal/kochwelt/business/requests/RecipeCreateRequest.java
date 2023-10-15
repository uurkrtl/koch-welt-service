package com.kartal.kochwelt.business.requests;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecipeCreateRequest {
	@Size(min = 3, message = "Der Name sollte mindestens 3 Zeichen lang sein")
	private String name;
	
	@Size(min = 3, message = "Der Name sollte mindestens 3 Zeichen lang sein")
	private String preparation;
	
	@Positive(message = "Die Vorbereitungszeit muss eine streng positive Zahl sein")
	private int preparationTime;
	
	private int bakingTime;
	
	private String imageUrl;
	private long userId;
	private long categoryId;
}