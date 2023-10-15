package com.kartal.kochwelt.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCreateRequest {
	private String title;
	private String description;
	private String imageUrl;
	private long userId;
}