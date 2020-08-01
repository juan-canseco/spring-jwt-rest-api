package com.bigiotech.taxiapp.security;

import com.bigiotech.taxiapp.controllers.AuthorizationRequest;
import com.bigiotech.taxiapp.domain.user.TokenDTO;
import com.bigiotech.taxiapp.domain.user.UserDTO;
import com.bigiotech.taxiapp.domain.user.UserService;
import com.bigiotech.taxiapp.services.TokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.bigiotech.taxiapp.security.SecurityConstants.HEADER_STRING;
import static com.bigiotech.taxiapp.security.SecurityConstants.TOKEN_PREFIX;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private TokenService tokenService;
    private UserService userService;


    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, TokenService tokenService, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userService = userService;
        super.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            AuthorizationRequest userCredentials = new ObjectMapper().readValue(request.getInputStream(), AuthorizationRequest.class);
            String username = userCredentials.getUsername();
            String password = userCredentials.getPassword();
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (IOException e) {
            return null;
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        String token = tokenService.generateToken(authResult);
        response.addHeader(HEADER_STRING , TOKEN_PREFIX + token);

        String username = tokenService.getUsername(token);
        UserDTO userDTO = userService.getUserByName(username);

        TokenDTO tokenDTO = new TokenDTO(token, userDTO);

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonToken = objectMapper.writeValueAsString(tokenDTO);

        response.getWriter().write(jsonToken);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
