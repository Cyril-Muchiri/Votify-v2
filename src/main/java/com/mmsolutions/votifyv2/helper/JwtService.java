package com.mmsolutions.votifyv2.helper;

import org.springframework.stereotype.Component;


@Component

public class JwtService {
    private final String SECRET_KEY = "my-secret-key";

    public String generateToken(String username) {
        return Jwt.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
