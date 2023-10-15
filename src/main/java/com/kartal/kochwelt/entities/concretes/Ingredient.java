package com.kartal.kochwelt.entities.concretes;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "ingredients")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ingredient {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "ingredient")
	private List<RecipeIngredient> ingredients;
}