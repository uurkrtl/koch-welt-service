package com.kartal.kochwelt.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartal.kochwelt.entities.concretes.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	boolean existsByName(String name);

}
