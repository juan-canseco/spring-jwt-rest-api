package com.bigiotech.taxiapp.services;

import org.springframework.security.core.Authentication;

public interface TokenService {
    String generateToken(Authentication authentication);
    String getUsername(String jwt);
    boolean validate(String token);
}
