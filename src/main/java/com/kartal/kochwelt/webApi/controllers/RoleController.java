package com.kartal.kochwelt.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kartal.kochwelt.business.abstracts.RoleService;
import com.kartal.kochwelt.business.responses.RolesGetAllResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RoleController {
	private RoleService roleService;
	
	@GetMapping
	public List<RolesGetAllResponse> getAll(){
		return this.roleService.getAll();
	}
}
