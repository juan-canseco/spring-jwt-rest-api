package com.bigiotech.taxiapp;

import com.bigiotech.taxiapp.domain.exceptions.EmailAlreadyExistsException;
import com.bigiotech.taxiapp.domain.exceptions.UsernameAlreadyExistsException;
import com.bigiotech.taxiapp.domain.user.*;
import com.bigiotech.taxiapp.services.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplIntegrationTest {

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @Before
    public void init() {

        User user = new User();
        user.setUsername("jcanseco");
        user.setEmail("jcanseco@hotmail.com");
        user.setPassword("someRandomPassword");

        Mockito.when(userRepository
                .findUserByUsername(user.getUsername()))
                .thenReturn(user);

        Mockito.when(userRepository
                .findUserByEmail(user.getEmail()))
                .thenReturn(user);
    }

    @Test
    public void whenFindingUserByName() {
        String username = "jcanseco";
        UserDTO found = userService.getUserByName(username);
        assertThat(found.getUsername()).isEqualTo(username);
    }

    @Test
    public void whenFindingUserByNameFails() {
        String username = "fake_user";
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.getUserByName(username);
        });
    }


    @Test
    public void shouldFindUserDetails() {
        String username = "jcanseco";
        UserDetails userDetails = userService.loadUserByUsername(username);
        assertThat(userDetails.getUsername()).isEqualTo(username);
    }

    @Test
    public void shouldFindUserDetailsFail() {
        String username = "fake_user";
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(username);
        });
    }

    @Test
    public void shouldRegisterUserSuccessfully()  {

        CreateUserDTO createUserDTO = new CreateUserDTO(
                "Juan Pablo",
                "Canseco Rios",
                "jcanseco117@hotmail.com",
                "jcanseco117",
                "1234",
                "foto");

        User user = userService.register(createUserDTO);
        assertThat(user).isNotNull();
    }

    @Test
    public void shouldUserRegistrationThrowsUsernameAlreadyExistsException() {

        CreateUserDTO createUserDTO = new CreateUserDTO(
                "Juan Pablo",
                "Canseco Rios",
                "jcanseco117@hotmail.com",
                "jcanseco",
                "1234",
                "foto");

        assertThrows(UsernameAlreadyExistsException.class, ()-> {
            userService.register(createUserDTO);
        });
    }

    @Test
    public void shouldUserRegistrationThrowsEmailAlreadyExistsException() {

        CreateUserDTO createUserDTO = new CreateUserDTO(
                "Juan Pablo",
                "Canseco Rios",
                "jcanseco@hotmail.com",
                "jcanseco117",
                "1234",
                "foto");

        assertThrows(EmailAlreadyExistsException.class, ()-> {
            userService.register(createUserDTO);
        });
    }

    @After
    public void end() {
        userRepository.deleteAll();
    }
}
