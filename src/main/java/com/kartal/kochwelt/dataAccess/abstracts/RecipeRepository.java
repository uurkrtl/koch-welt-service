package com.kartal.kochwelt.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.kartal.kochwelt.entities.concretes.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

	boolean existsByName(String name);

	List<Recipe> findAllByCategoryId(Long categoryId, Sort sort);

	List<Recipe> findAllByCategoryId(Long categoryId, PageRequest of);

}