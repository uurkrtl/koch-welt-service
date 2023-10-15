package com.kartal.kochwelt.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateRequest {
	private long id;
	private String title;
	private String description;
	private long readCount;
	private String imageUrl;
	private boolean isActive;
	private long userId;
}