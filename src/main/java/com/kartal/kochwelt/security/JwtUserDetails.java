package com.kartal.kochwelt.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.kartal.kochwelt.entities.concretes.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JwtUserDetails implements UserDetails {
	private long id;
	private String username;
	private String password;
	private boolean isEnabled;
	private Collection<? extends GrantedAuthority> authorities;
	
	public static JwtUserDetails create(User user) {
		List<GrantedAuthority> authoritiesList = new ArrayList<>();
		String role = user.getRole().getName();
		authoritiesList.add(new SimpleGrantedAuthority(role));
		return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(), user.isEnabled(),authoritiesList);
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
}