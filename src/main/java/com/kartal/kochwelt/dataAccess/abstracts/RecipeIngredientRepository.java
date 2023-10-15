package com.kartal.kochwelt.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartal.kochwelt.entities.concretes.RecipeIngredient;
import com.kartal.kochwelt.entities.concretes.RecipeIngredientKey;

public interface RecipeIngredientRepository extends JpaRepository<RecipeIngredient, RecipeIngredientKey> {

	List<RecipeIngredient> findByRecipeId(long recipeId);

}
