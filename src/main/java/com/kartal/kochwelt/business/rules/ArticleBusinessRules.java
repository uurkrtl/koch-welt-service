package com.kartal.kochwelt.business.rules;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.kartal.kochwelt.core.exception.BadRequestException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleBusinessRules {
	public void checkIfOrderByNull(Optional<String> orderBy, Optional<Integer> limit) {
		if(orderBy.isEmpty() && limit.isPresent()) {
			throw new BadRequestException("To be a limit, sort criteria must be entered.");
		}
	}
}