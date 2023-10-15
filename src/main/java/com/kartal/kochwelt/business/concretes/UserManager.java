package com.kartal.kochwelt.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kartal.kochwelt.business.abstracts.RoleRepository;
import com.kartal.kochwelt.business.abstracts.UserService;
import com.kartal.kochwelt.business.requests.UserCreateRequest;
import com.kartal.kochwelt.business.requests.UserLoginRequest;
import com.kartal.kochwelt.business.requests.UserUpdateRequest;
import com.kartal.kochwelt.business.responses.UserGetByIdResponse;
import com.kartal.kochwelt.business.responses.UsersGetAllResponse;
import com.kartal.kochwelt.business.rules.UserBusinessRules;
import com.kartal.kochwelt.core.utilities.mappers.ModelMapperService;
import com.kartal.kochwelt.dataAccess.abstracts.UserRepository;
import com.kartal.kochwelt.entities.concretes.User;
import com.kartal.kochwelt.security.JwtTokenProvider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
//@AllArgsConstructor
public class UserManager implements UserService {
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private ModelMapperService modelMapperService;
	private AuthenticationManager authenticationManager;
	private JwtTokenProvider jwtTokenProvider;
	private PasswordEncoder passwordEncoder;
	private UserBusinessRules userBusinessRules;
	
	public UserManager(UserRepository userRepository, RoleRepository roleRepository,
			ModelMapperService modelMapperService, AuthenticationManager authenticationManager,
			JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder, UserBusinessRules userBusinessRules) {
		super();
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.modelMapperService = modelMapperService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.passwordEncoder = passwordEncoder;
		this.userBusinessRules = userBusinessRules;
	}
	
	@Value("${kochwelt.app.secret}")
	private String APP_SECRET;
	

	@Override
	public List<UsersGetAllResponse> getAll() {
		List<User>users = this.userRepository.findAll();
		List<UsersGetAllResponse> usersGetAllRespons = users.stream().map(user->this.modelMapperService.forResponse().map(user, UsersGetAllResponse.class)).collect(Collectors.toList());
		return usersGetAllRespons;
	}

	@Override
	public UserGetByIdResponse getById(long id) {
		userBusinessRules.checkIfUserNameByIdNotFound(id);
		User user = this.userRepository.findById(id).orElse(null);
		UserGetByIdResponse userGetByIdResponse = this.modelMapperService.forResponse().map(user, UserGetByIdResponse.class);
		return userGetByIdResponse;
	}

	@Override
	public void update(UserUpdateRequest userUpdateRequest) {
		userBusinessRules.checkIfUserNameNotFound(userUpdateRequest.getUserName());
		User user = new User();
		user.setId(userUpdateRequest.getId());
		user.setUserName(userUpdateRequest.getUserName());
		user.setFirstName(userUpdateRequest.getFirstName());
		user.setLastName(userUpdateRequest.getLastName());
		user.setImageUrl(userUpdateRequest.getImageUrl());
		user.setEnabled(userUpdateRequest.isEnabled());
		user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
		user.setRole(this.roleRepository.findById(userUpdateRequest.getRoleId()).orElse(null));
		this.userRepository.save(user);
	}

	@Override
	public void delete(long id) {
		userBusinessRules.checkIfUserNameByIdNotFound(id);
		User user = this.userRepository.findById(id).orElse(null);
		user.setEnabled(false);
		this.userRepository.save(user);
	}

	@Override
	public String login(UserLoginRequest userLoginRequest) {
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userLoginRequest.getUserName(), userLoginRequest.getPassword());
		Authentication authentication =  authenticationManager.authenticate(usernamePasswordAuthenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwtToken = jwtTokenProvider.genereteJwtToken(authentication);
		return jwtToken;
	}

	@Override
	public void add(UserCreateRequest userCreateRequest) {
		userBusinessRules.checkIfUserNameExists(userCreateRequest.getUserName());
		User user = new User();
		user.setUserName(userCreateRequest.getUserName());
		user.setPassword(passwordEncoder.encode(userCreateRequest.getPassword()));
		user.setFirstName(userCreateRequest.getFirstName());
		user.setLastName(userCreateRequest.getLastName());
		user.setImageUrl(userCreateRequest.getImageUrl());
		user.setRole(this.roleRepository.findById(userCreateRequest.getRoleId()).orElse(null));
		user.setEnabled(true);
		this.userRepository.save(user);
	}
	
	@Override
	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}

}