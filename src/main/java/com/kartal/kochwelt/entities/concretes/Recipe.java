package com.kartal.kochwelt.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "reciples")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "preparation", length = 3000)
	private String preparation;
	
	@Column(name = "preparation_time")
	private int preparationTime;
	
	@Column(name = "baking_time")
	private int bakingTime;
	
	@Column(name = "insertion_date")
	private LocalDate insertionDate;
	
	@Column(name = "update_date")
	private LocalDate updateDate;
	
	@Column(name = "read_count")
	private long readCount;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "recipe_week")
	private boolean recipeWeek;
	
	@Column(name = "recipe_day")
	private boolean recipeDay;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "recipe")
	private List<RecipeIngredient> ingredients;
}