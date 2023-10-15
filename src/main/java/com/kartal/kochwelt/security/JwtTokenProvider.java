package com.kartal.kochwelt.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
@ComponentScan("com.kartal.kochwelt")
public class JwtTokenProvider {
	@Value("${kochwelt.app.secret}")
	private String APP_SECRET;
	@Value("${kochwelt.expires.in}")
	private long EXPIRES_IN;
	
	public String genereteJwtToken(Authentication auth) {
		JwtUserDetails jwtUserDetails = (JwtUserDetails) auth.getPrincipal();
		Date expireDate = new Date(new Date().getTime()+EXPIRES_IN);
		return Jwts.builder().setSubject(Long.toString(jwtUserDetails.getId()))
				.setIssuedAt(new Date()).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
	}
	
	Long getUserIdFromJwt(String token) {
		Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
		return Long.parseLong(claims.getSubject());
	}
	
	boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
			return !isTokenExpired(token);
		}catch(SignatureException e) {
			return false;
		}catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
	}
	
	private boolean isTokenExpired(String token) {
		Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
		return expiration.before(new Date());
	}
}