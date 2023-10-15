package com.kartal.kochwelt.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartal.kochwelt.entities.concretes.User;

public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByUserName(String userName);

	User findByUserName(String username);

}
