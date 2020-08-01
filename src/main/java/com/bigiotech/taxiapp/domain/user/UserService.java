package com.bigiotech.taxiapp.domain.user;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User register(CreateUserDTO dto);
    void delete(String userId);
    UserDTO getUserById(String userId);
    UserDTO getUserByName(String username);
}
