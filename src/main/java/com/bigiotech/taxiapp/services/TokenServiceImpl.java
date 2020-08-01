package com.bigiotech.taxiapp.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import java.security.Key;
import java.util.Date;
import static com.bigiotech.taxiapp.security.SecurityConstants.EXPIRATION_TIME;
import static com.bigiotech.taxiapp.security.SecurityConstants.SECRET;

public class TokenServiceImpl implements TokenService {

    @Override
    public String generateToken(Authentication authentication) {
        return Jwts.builder()
                .setSubject(authentication.getName())
                .signWith(getSignKey())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setIssuer("taxi-app")
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }

    @Override
    public String getUsername(String token) {
        JwtParser parser = Jwts.parserBuilder().setSigningKey(getSignKey()).build();
        Jws<Claims> claimsJws = parser.parseClaimsJws(token);
        return claimsJws.getBody().getSubject();
    }

    private Key getSignKey() {
        byte[] keyBytes = SECRET.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public boolean validate(String token) {
        try {
            JwtParser parser = Jwts.parserBuilder().setSigningKey(getSignKey()).build();
            Claims claims = parser.parseClaimsJws(token).getBody();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
