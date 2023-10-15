package com.kartal.kochwelt.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartal.kochwelt.entities.concretes.Article;

public interface ArticleRepository extends JpaRepository<Article, Long>{

}
