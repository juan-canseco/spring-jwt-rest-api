package com.bigiotech.taxiapp;

import com.bigiotech.taxiapp.domain.user.User;
import com.bigiotech.taxiapp.domain.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class UserRepositoryIntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void contextLoads() {

    }

    @Test
    public void whenFindByUsernameThenReturnUser() {
        String username = "jcanseco";
        User user = new User();
        user.setUsername(username);
        userRepository.save(user);
        User found = userRepository.findUserByUsername(username);
        userRepository.deleteById(found.getId());
        assertThat(found.getUsername()).isEqualTo(username);
    }

    @Test
    public void whenFindingEmailThenReturnUser() {
        String email = "juan@hotmail.com";
        User user = new User();
        user.setEmail(email);
        userRepository.save(user);
        User found = userRepository.findUserByEmail(email);
        userRepository.delete(found);
        assertThat(found.getEmail()).isEqualTo(email);
    }
}
