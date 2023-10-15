package com.kartal.kochwelt.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartal.kochwelt.entities.concretes.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
