package com.kartal.kochwelt.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kartal.kochwelt.business.abstracts.RoleRepository;
import com.kartal.kochwelt.business.abstracts.RoleService;
import com.kartal.kochwelt.business.responses.RolesGetAllResponse;
import com.kartal.kochwelt.core.utilities.mappers.ModelMapperService;
import com.kartal.kochwelt.entities.concretes.Role;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleManager implements RoleService {
	private RoleRepository roleRepository;
	private ModelMapperService modelMapperService;

	@Override
	public List<RolesGetAllResponse> getAll() {
		List<Role> roles = this.roleRepository.findAll();
		List<RolesGetAllResponse> rolesGetAllResponse = roles.stream().map(role->this.modelMapperService.forResponse().map(role, RolesGetAllResponse.class)).collect(Collectors.toList());
		return rolesGetAllResponse;
	}
}