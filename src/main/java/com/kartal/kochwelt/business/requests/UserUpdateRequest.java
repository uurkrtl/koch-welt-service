package com.kartal.kochwelt.business.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateRequest {
	private long id;
	
	@Size(min = 3, message = "Der Username sollte mindestens 3 Zeichen lang sein")
	private String userName;
	
	@NotNull(message = "Passwort kann nicht leer sein")
	private String password;
	
	@Size(min = 3, message = "Der Vorname sollte mindestens 3 Zeichen lang sein")
	private String firstName;
	
	@Size(min = 3, message = "Der Nachname sollte mindestens 3 Zeichen lang sein")
	private String lastName;
	private String imageUrl;
	private boolean isEnabled;
	
	@Positive(message = "Die Rollen-ID muss eine streng positive Zahl sein")
	private long roleId;
}