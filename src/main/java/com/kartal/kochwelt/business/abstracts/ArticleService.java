package com.kartal.kochwelt.business.abstracts;

import java.util.List;
import java.util.Optional;

import com.kartal.kochwelt.business.requests.ArticleCreateRequest;
import com.kartal.kochwelt.business.requests.ArticleUpdateRequest;
import com.kartal.kochwelt.business.responses.ArticleGetByIdResponse;
import com.kartal.kochwelt.business.responses.ArticlesGetAllResponse;

public interface ArticleService {
	List<ArticlesGetAllResponse> getAll(Optional<String> orderBy, Optional<Integer> limit);
	ArticleGetByIdResponse getById(long id);
	public void add(ArticleCreateRequest articleCreateRequest);
	public void update(ArticleUpdateRequest articleUpdateRequest);
	public void delete(long id);
	public ArticleGetByIdResponse readCounter(long id);
}