package com.kartal.kochwelt.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kartal.kochwelt.business.abstracts.CategoryService;
import com.kartal.kochwelt.business.requests.CategoryCreateRequest;
import com.kartal.kochwelt.business.requests.CategoryUpdateRequest;
import com.kartal.kochwelt.business.responses.CategoriesGetAllResponse;
import com.kartal.kochwelt.business.responses.CategoryGetByIdResponse;
import com.kartal.kochwelt.core.utilities.mappers.ModelMapperService;
import com.kartal.kochwelt.dataAccess.abstracts.CategoryRepository;
import com.kartal.kochwelt.entities.concretes.Category;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {
	private CategoryRepository categoryRepository;
	private ModelMapperService modelMapperService;
	@Override
	public List<CategoriesGetAllResponse> getAll() {
		List<Category> categories = this.categoryRepository.findAll();
		List<CategoriesGetAllResponse> categoriesGetAllRespons = categories.stream().map(category->this.modelMapperService.forResponse().map(category, CategoriesGetAllResponse.class)).collect(Collectors.toList());
		return categoriesGetAllRespons;
	}
	@Override
	public CategoryGetByIdResponse getById(long id) {
		Category category = this.categoryRepository.findById(id).orElse(null);
		CategoryGetByIdResponse categoryGetByIdResponse = this.modelMapperService.forResponse().map(category, CategoryGetByIdResponse.class);
		return categoryGetByIdResponse;
	}
	@Override
	public void add(CategoryCreateRequest categoryCreateRequest) {
		Category category = this.modelMapperService.forRequest().map(categoryCreateRequest, Category.class);
		category.setActive(true);
		this.categoryRepository.save(category);
		
	}
	@Override
	public void update(CategoryUpdateRequest categoryUpdateRequest) {
		Category category = new Category();
		category.setId(categoryUpdateRequest.getId());
		category.setName(categoryUpdateRequest.getName());
		category.setImageUrl(categoryUpdateRequest.getImageUrl());
		this.categoryRepository.save(category);
		
	}
	@Override
	public void delete(long id) {
		Category category = this.categoryRepository.findById(id).orElse(null);
		category.setActive(false);
		this.categoryRepository.save(category);
	}
}