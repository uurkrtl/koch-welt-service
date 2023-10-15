package com.kartal.kochwelt.entities.concretes;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "articles")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description", length = 10000)
	private String description;
	
	@Column(name = "insertion_date")
	private LocalDate insertionDate;
	
	@Column(name = "update_date")
	private LocalDate updateDate;
	
	@Column(name = "read_count")
	private long readCount;
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "is_active")
	private boolean isActive;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}