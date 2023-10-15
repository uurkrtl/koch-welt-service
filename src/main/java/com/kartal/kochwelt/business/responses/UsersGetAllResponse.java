package com.kartal.kochwelt.business.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersGetAllResponse {
	private long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String imageUrl;
	private String roleName;
	private boolean isEnabled;
}