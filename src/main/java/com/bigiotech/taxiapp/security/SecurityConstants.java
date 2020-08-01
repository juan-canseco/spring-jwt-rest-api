package com.bigiotech.taxiapp.security;

public class SecurityConstants {

    public static final String SECRET = "n2r5u8x/A?D(G+KbPeSgVkYp3s6v9y$B";

    public static final long EXPIRATION_TIME = 864_000_00; // 10 Days

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING ="Authorization";

    public static final String SIGN_UP_URL = "/users/register";

    public static final String LOGIN_URL = "/users/auth";
}
