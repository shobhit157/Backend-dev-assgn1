package com.example.Backend_dev_assgn.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Backend_dev_assgn.dto.AuthResponse;
import com.example.Backend_dev_assgn.dto.LoginRequest;
import com.example.Backend_dev_assgn.dto.RegisterRequest;
import com.example.Backend_dev_assgn.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	   private final AuthService authService;

       public AuthController(AuthService authService) {
        this.authService = authService;
      }

	
	  
	
	   @PostMapping("/register")
	    public String register(@Valid @RequestBody RegisterRequest request){
		   
	        return authService.register(request);
	    }

	    @PostMapping("/login")
	    public AuthResponse login(@Valid @RequestBody LoginRequest request){
	    	
	        return authService.login(request);
	    }

}
