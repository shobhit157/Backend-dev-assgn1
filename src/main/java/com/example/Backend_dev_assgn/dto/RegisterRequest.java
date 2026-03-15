package com.example.Backend_dev_assgn.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	
	@NotEmpty(message = "Username is required")
	private String username;
	
	@Email(message = "Invalid email format")
    private String email;
	
	 @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

}
