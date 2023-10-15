package com.kartal.kochwelt.business.abstracts;

import java.util.List;

import com.kartal.kochwelt.business.responses.RolesGetAllResponse;

public interface RoleService {
	List<RolesGetAllResponse> getAll();
}