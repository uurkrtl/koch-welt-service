package com.kartal.kochwelt.webApi.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kartal.kochwelt.business.abstracts.UserService;
import com.kartal.kochwelt.business.requests.UserCreateRequest;
import com.kartal.kochwelt.business.requests.UserLoginRequest;
import com.kartal.kochwelt.business.requests.UserUpdateRequest;
import com.kartal.kochwelt.business.responses.UserGetByIdResponse;
import com.kartal.kochwelt.business.responses.UsersGetAllResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
	private UserService userService;
	
	@PostMapping("/login")
	public String login(@RequestBody UserLoginRequest userLoginRequest) {
		return this.userService.login(userLoginRequest);
	}
	
	@PostMapping("/register")
	public void add(@Valid @RequestBody UserCreateRequest userCreateRequest) {
		this.userService.add(userCreateRequest);
	}
	
	@GetMapping("/getId")
	public Long getUserIdFromToken(@RequestParam String token) {
		return this.userService.getUserIdFromToken(token);
	}
	
	@GetMapping
	public List<UsersGetAllResponse> getAll(){
		return this.userService.getAll();
	}
	
	@GetMapping("/{id}")
	public UserGetByIdResponse getById(@RequestParam long id) {
		return this.userService.getById(id);
	}
	
	@PutMapping("/update")
	public void update (@RequestBody UserUpdateRequest userUpdateRequest) {
		this.userService.update(userUpdateRequest);
	}
	
	@PutMapping("/delete")
	public void delete (@RequestParam long id) {
		this.userService.delete(id);
	}
}