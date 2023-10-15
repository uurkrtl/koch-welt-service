package com.kartal.kochwelt.business.concretes;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.kartal.kochwelt.business.rules.UserBusinessRules;
import com.kartal.kochwelt.dataAccess.abstracts.UserRepository;
import com.kartal.kochwelt.entities.concretes.User;
import com.kartal.kochwelt.security.JwtUserDetails;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserDetailsManager implements UserDetailsService {
	private UserRepository userRepository;
	private UserBusinessRules userBusinessRules;
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		userBusinessRules.checkIfUserNameNotFound(username);
		User user = userRepository.findByUserName(username);
		return JwtUserDetails.create(user);
	}
	
	public UserDetails loadUserById(long id) {
		userBusinessRules.checkIfUserNameByIdNotFound(id);
		User user = userRepository.findById(id).orElse(null);
		return JwtUserDetails.create(user);
	}
}