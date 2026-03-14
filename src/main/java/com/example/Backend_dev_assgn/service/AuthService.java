package com.example.Backend_dev_assgn.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Backend_dev_assgn.dto.AuthResponse;
import com.example.Backend_dev_assgn.dto.LoginRequest;
import com.example.Backend_dev_assgn.dto.RegisterRequest;
import com.example.Backend_dev_assgn.entity.Role;
import com.example.Backend_dev_assgn.entity.User;
import com.example.Backend_dev_assgn.repository.UserRepository;
import com.example.Backend_dev_assgn.security.JwtUtil;

@Service
public class AuthService {
	
	  private final UserRepository userRepository;
	  private final BCryptPasswordEncoder passwordEncoder;
	  private final JwtUtil jwtUtil;
	  
	  public AuthService(UserRepository userRepository,
              BCryptPasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
             this.userRepository = userRepository;
             this.passwordEncoder = passwordEncoder;
             this.jwtUtil=jwtUtil;
      }

      public String register(RegisterRequest request) {

       if(userRepository.findByUsername(request.getUsername()).isPresent()){
        return "Username already exists";
       }  

       User user = new User();
       user.setUsername(request.getUsername());
       user.setEmail(request.getEmail());

       user.setPassword(passwordEncoder.encode(request.getPassword()));

       user.setRole(Role.USER);

       userRepository.save(user);

       return "User registered successfully";
     }
      
      public AuthResponse login(LoginRequest request) {

    	    User user = userRepository.findByUsername(request.getUsername())
    	            .orElseThrow(() -> new RuntimeException("User not found"));

    	    // check password
    	    if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
    	        throw new RuntimeException("Invalid password");
    	    }

    	    String token = jwtUtil.generateToken(user.getUsername());

    	    return new AuthResponse(token);
    	}

}
