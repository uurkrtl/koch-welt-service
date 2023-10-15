package com.kartal.kochwelt.business.responses;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleGetByIdResponse {
	private long id;
	private String title;
	private String description;
	private LocalDate insertionDate;
	private LocalDate updateDate;
	private long readCount;
	private String imageUrl;
	private String userFirstName;
	private String userLastName;
	private String userUserName;
	private String userImageUrl;
}