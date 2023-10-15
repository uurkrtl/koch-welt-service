package com.kartal.kochwelt.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientsGetAllResponse {
	private long id;
	private String name;
}