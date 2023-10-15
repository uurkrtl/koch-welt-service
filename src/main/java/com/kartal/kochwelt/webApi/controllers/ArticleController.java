package com.kartal.kochwelt.webApi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kartal.kochwelt.business.abstracts.ArticleService;
import com.kartal.kochwelt.business.requests.ArticleCreateRequest;
import com.kartal.kochwelt.business.requests.ArticleUpdateRequest;
import com.kartal.kochwelt.business.responses.ArticleGetByIdResponse;
import com.kartal.kochwelt.business.responses.ArticlesGetAllResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/articles")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class ArticleController {
	private ArticleService articleService;
	
	@GetMapping
	List<ArticlesGetAllResponse> getAll(@RequestParam Optional<String> orderBy, Optional<Integer> limit){
		return this.articleService.getAll(orderBy, limit);
	}
	
	@GetMapping("/getById")
	ArticleGetByIdResponse getById(@RequestParam long id) {
		return this.articleService.getById(id);
	}
	
	@PostMapping("/add")
	public void add(@RequestBody ArticleCreateRequest articleCreateRequest) {
		this.articleService.add(articleCreateRequest);
	}
	
	@PutMapping("/update")
	public void update(@RequestBody ArticleUpdateRequest articleUpdateRequest) {
		this.articleService.update(articleUpdateRequest);
	}
	
	@PutMapping("/{id}")
	public void delete(@RequestParam long id) {
		this.articleService.delete(id);
	}
	
	@PutMapping("readCounter")
	public ArticleGetByIdResponse readCounter( @RequestParam long id) {
		return this.articleService.readCounter(id);
	}
}