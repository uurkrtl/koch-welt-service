package com.kartal.kochwelt.business.rules;

import org.springframework.stereotype.Service;

import com.kartal.kochwelt.core.exception.BadRequestException;
import com.kartal.kochwelt.core.exception.NotFoundException;
import com.kartal.kochwelt.dataAccess.abstracts.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserBusinessRules {
	private UserRepository userRepository;
	
	public void checkIfUserNameExists(String userName) {
		if(this.userRepository.existsByUserName(userName)) {
			throw new BadRequestException("Username existiert bereits");
		}
	}
	
	public void checkIfUserNameNotFound(String userName) {
		if(!this.userRepository.existsByUserName(userName)) {
			throw new NotFoundException("User nicht gefunden. Gesuchter Username: " + userName);
		}
	}
	
	public void checkIfUserNameByIdNotFound(long id) {
		if(!this.userRepository.existsById(id)) {
			throw new NotFoundException("User nicht gefunden. Gesuchte ID: " + id);
		}
	}
}