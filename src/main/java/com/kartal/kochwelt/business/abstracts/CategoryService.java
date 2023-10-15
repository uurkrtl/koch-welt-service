package com.kartal.kochwelt.business.abstracts;

import java.util.List;

import com.kartal.kochwelt.business.requests.CategoryCreateRequest;
import com.kartal.kochwelt.business.requests.CategoryUpdateRequest;
import com.kartal.kochwelt.business.responses.CategoriesGetAllResponse;
import com.kartal.kochwelt.business.responses.CategoryGetByIdResponse;

public interface CategoryService {
	List<CategoriesGetAllResponse> getAll();
	CategoryGetByIdResponse getById(long id);
	public void add(CategoryCreateRequest categoryCreateRequest);
	public void update(CategoryUpdateRequest categoryUpdateRequest);
	public void delete(long id);
}