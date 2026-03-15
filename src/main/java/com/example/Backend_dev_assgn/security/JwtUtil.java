package com.example.Backend_dev_assgn.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {
	
	@Value("${jwt.secret}")
    private String secretKey;


	    public String generateToken(String username) {

	        return Jwts.builder()
	                .setSubject(username)
	                .setIssuedAt(new Date())
	                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
	                .signWith(SignatureAlgorithm.HS256, secretKey)
	                .compact();
	    }

	    public String extractUsername(String token) {

	        Claims claims = Jwts.parser()
	                .setSigningKey(secretKey).build()
	                .parseClaimsJws(token)
	                .getBody();

	        return claims.getSubject();
	    }

}
