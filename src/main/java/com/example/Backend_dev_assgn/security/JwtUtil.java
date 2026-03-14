package com.example.Backend_dev_assgn.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	 private final String SECRET_KEY = "myverysecuresecretkeythatshouldbeatleast32chars";

	    public String generateToken(String username) {

	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
	                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
	                .compact();
	    }

	    public String extractUsername(String token) {

	        Claims claims = Jwts.parser()
	                .setSigningKey(SECRET_KEY).build()
	                .parseClaimsJws(token)
	                .getBody();

	        return claims.getSubject();
	    }

}
