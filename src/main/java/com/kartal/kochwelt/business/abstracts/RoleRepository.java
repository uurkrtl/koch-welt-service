package com.kartal.kochwelt.business.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kartal.kochwelt.entities.concretes.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
