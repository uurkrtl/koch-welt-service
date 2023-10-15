package com.kartal.kochwelt.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.kartal.kochwelt.business.abstracts.ArticleService;
import com.kartal.kochwelt.business.requests.ArticleCreateRequest;
import com.kartal.kochwelt.business.requests.ArticleUpdateRequest;
import com.kartal.kochwelt.business.responses.ArticleGetByIdResponse;
import com.kartal.kochwelt.business.responses.ArticlesGetAllResponse;
import com.kartal.kochwelt.business.rules.ArticleBusinessRules;
import com.kartal.kochwelt.core.utilities.mappers.ModelMapperService;
import com.kartal.kochwelt.dataAccess.abstracts.ArticleRepository;
import com.kartal.kochwelt.dataAccess.abstracts.UserRepository;
import com.kartal.kochwelt.entities.concretes.Article;
import com.kartal.kochwelt.entities.concretes.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ArticleManager implements ArticleService {
	private ArticleRepository articleRepository;
	private UserRepository userRepository;
	private ModelMapperService modelMapperService;
	private ArticleBusinessRules articleBusinessRules;

	@Override
	public List<ArticlesGetAllResponse> getAll(Optional<String> orderBy, Optional<Integer> limit) {
		this.articleBusinessRules.checkIfOrderByNull(orderBy, limit);
		
		List<Article> articles = this.articleRepository.findAll();
		List<ArticlesGetAllResponse> articlesGetAllRespons = articles.stream().map(article->this.modelMapperService.forResponse().map(article, ArticlesGetAllResponse.class)).collect(Collectors.toList());
		
		if(orderBy.isPresent() && limit.isEmpty()) {
			articles = this.articleRepository.findAll(Sort.by(Sort.Direction.DESC, orderBy.get()));
			articlesGetAllRespons = articles.stream().map(article->this.modelMapperService.forResponse().map(article, ArticlesGetAllResponse.class)).collect(Collectors.toList());
		}else if(orderBy.isPresent() && limit.isPresent()) {
			Page<Article> articlesPage = this.articleRepository.findAll(PageRequest.of(0, limit.get(),Sort.by(Sort.Direction.DESC, orderBy.get())));
			articlesGetAllRespons = articlesPage.stream().map(article->this.modelMapperService.forResponse().map(article, ArticlesGetAllResponse.class)).collect(Collectors.toList());
		}
		return articlesGetAllRespons;
	}

	@Override
	public ArticleGetByIdResponse getById(long id) {
		Article article = this.articleRepository.findById(id).orElse(null);
		ArticleGetByIdResponse articleGetByIdResponse = this.modelMapperService.forResponse().map(article, ArticleGetByIdResponse.class);
		return articleGetByIdResponse;
	}

	@Override
	public void add(ArticleCreateRequest articleCreateRequest) {
		Article article = new Article();
		User foundUser = this.userRepository.findById(articleCreateRequest.getUserId()).orElse(null);
		article.setTitle(articleCreateRequest.getTitle());
		article.setDescription(articleCreateRequest.getDescription());
		article.setImageUrl(articleCreateRequest.getImageUrl());
		article.setUser(foundUser);
		article.setActive(true);
		article.setInsertionDate(LocalDate.now());
		this.articleRepository.save(article);
	}

	@Override
	public void update(ArticleUpdateRequest articleUpdateRequest) {
		Article article = new Article();
		Article foundArticle = this.articleRepository.findById(articleUpdateRequest.getId()).orElse(null);
		User foundUser = this.userRepository.findById(articleUpdateRequest.getUserId()).orElse(null);
		article.setId(articleUpdateRequest.getId());
		article.setTitle(articleUpdateRequest.getTitle());
		article.setDescription(articleUpdateRequest.getDescription());
		article.setInsertionDate(foundArticle.getInsertionDate());
		article.setUpdateDate(LocalDate.now());
		article.setReadCount(foundArticle.getReadCount());
		article.setImageUrl(articleUpdateRequest.getImageUrl());
		article.setActive(articleUpdateRequest.isActive());
		article.setUser(foundUser);
		this.articleRepository.save(article);
	}

	@Override
	public void delete(long id) {
		Article article = this.articleRepository.findById(id).orElse(null);
		article.setActive(false);
		this.articleRepository.save(article);
	}
	
	@Override
	public ArticleGetByIdResponse readCounter(long id) {
		Article article = this.articleRepository.findById(id).orElse(null);
		long newReadCount = article.getReadCount()+1;
		article.setReadCount(newReadCount);
		this.articleRepository.save(article);
		ArticleGetByIdResponse articleGetByIdResponse = this.modelMapperService.forResponse().map(article, ArticleGetByIdResponse.class);
		return articleGetByIdResponse;
	}
}