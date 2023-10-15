package com.kartal.kochwelt.business.abstracts;

import java.util.List;

import com.kartal.kochwelt.business.requests.UserCreateRequest;
import com.kartal.kochwelt.business.requests.UserLoginRequest;
import com.kartal.kochwelt.business.requests.UserUpdateRequest;
import com.kartal.kochwelt.business.responses.UserGetByIdResponse;
import com.kartal.kochwelt.business.responses.UsersGetAllResponse;

public interface UserService {
	String login(UserLoginRequest userLoginRequest);
	List<UsersGetAllResponse> getAll();
	UserGetByIdResponse getById(long id);
	Long getUserIdFromToken(String token);
	public void add(UserCreateRequest userCreateRequest);
	public void update(UserUpdateRequest userUpdateRequest);
	public void delete(long id);
}