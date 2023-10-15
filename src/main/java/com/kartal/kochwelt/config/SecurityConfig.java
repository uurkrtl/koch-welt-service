package com.kartal.kochwelt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.kartal.kochwelt.security.JwtAuthenticationEntryPoint;
import com.kartal.kochwelt.security.JwtAuthenticationFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
private JwtAuthenticationEntryPoint handler;
	
	@Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
    	return new JwtAuthenticationFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        //config.addAllowedOrigin("*");
        config.addAllowedOriginPattern("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity
    		.cors()
    		.and()
    		.csrf().disable()
    		.exceptionHandling().authenticationEntryPoint(handler).and()
    		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
    		.authorizeRequests()
    		.requestMatchers("/api/auth/login").permitAll()
    		.requestMatchers("/api/auth/register").permitAll()
    		.requestMatchers("/api/auth/getId").permitAll()
    		.requestMatchers("/api/recipes").permitAll()
    		.requestMatchers(HttpMethod.GET, "/api/recipes/**").permitAll()
    		.requestMatchers(HttpMethod.GET, "/api/articles/**").permitAll()
    		.requestMatchers("/api/articles/readCounter*").permitAll()
    		.requestMatchers("/api/articles/readCounter").permitAll()
    		.requestMatchers("/api/recipes/readCounter").permitAll()
    		.requestMatchers("/api/recipeIngredients/**").permitAll()
    		.requestMatchers("/api/articles").permitAll()
    		.requestMatchers("/api/categories").permitAll()
    		.requestMatchers("/api/ingredients").permitAll()
    		//.requestMatchers(HttpMethod.POST, "/api/products/add").permitAll()
    		.anyRequest().authenticated();
    		
    	httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    	return httpSecurity.build();
    }
}