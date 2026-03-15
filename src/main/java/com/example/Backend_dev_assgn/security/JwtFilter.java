package com.example.Backend_dev_assgn.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter{
	
	 private final JwtUtil jwtUtil;

	    public JwtFilter(JwtUtil jwtUtil) {
	        this.jwtUtil = jwtUtil;
	    }
	 
	 @Override
	 protected void doFilterInternal(HttpServletRequest request,
                HttpServletResponse response,
                FilterChain filterChain)
          throws ServletException, IOException, java.io.IOException {
		 
		 String path = request.getServletPath();

		    // Skip authentication endpoints
		    if (path.startsWith("/api/auth")) {
		        filterChain.doFilter(request, response);
		        return;
		    }

        String authHeader = request.getHeader("Authorization");

        if(authHeader != null && authHeader.startsWith("Bearer ")){

        String token = authHeader.substring(7);

        try{
          String username = jwtUtil.extractUsername(token);
          
          UsernamePasswordAuthenticationToken authToken =
                  new UsernamePasswordAuthenticationToken(
                          username,
                          null,
                          null
                  );

          authToken.setDetails(
                  new WebAuthenticationDetailsSource().buildDetails(request)
          );

          SecurityContextHolder.getContext().setAuthentication(authToken);

// here we could set authentication context later
          System.out.println("Authenticated user: " + username);

        }catch(Exception e){
          System.out.println("Invalid JWT token");
        }
       }

      filterChain.doFilter(request, response);
      }

}
