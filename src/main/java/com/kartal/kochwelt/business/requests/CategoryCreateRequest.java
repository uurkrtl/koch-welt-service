package com.kartal.kochwelt.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryCreateRequest {
	private String name;
	private String imageUrl;
}