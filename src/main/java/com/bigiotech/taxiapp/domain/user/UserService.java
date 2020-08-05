package com.bigiotech.taxiapp.domain.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import java.util.List;

public interface UserService extends UserDetailsService {

    User register(CreateUserDTO dto);
    void delete(String userId);

    UserDTO getUserById(String userId);
    UserDTO getUserByName(String username);

    List<UserDTO> getUsers(int pageIndex, int pageSize);
}
