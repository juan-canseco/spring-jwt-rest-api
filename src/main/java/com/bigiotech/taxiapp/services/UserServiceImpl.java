package com.bigiotech.taxiapp.services;

import com.bigiotech.taxiapp.domain.exceptions.EmailAlreadyExistsException;
import com.bigiotech.taxiapp.domain.exceptions.RecordNotFoundException;
import com.bigiotech.taxiapp.domain.exceptions.UsernameAlreadyExistsException;
import com.bigiotech.taxiapp.domain.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    private boolean checkIfUsernameExists(String username) {
        User user = repository.findUserByUsername(username);
        return user != null;
    }

    private boolean checkIfEmailExists(String email) {
        User user = repository.findUserByEmail(email);
        return user != null;
    }

    @Override
    public User register(CreateUserDTO dto) {

        if (checkIfEmailExists(dto.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        if (checkIfUsernameExists(dto.getUsername())) {
            throw new UsernameAlreadyExistsException("The given username is in use " + dto.getUsername());
        }

        String hashedPassword = passwordEncoder.encode(dto.getPassword());

        User user = new User();
        user.setNames(dto.getNames());
        user.setSurnames(dto.getSurnames());
        user.setUsername(dto.getUsername());
        user.setPassword(hashedPassword);
        user.setEmail(dto.getEmail());
        user.setProfilePicture(dto.getProfilePicture());

        repository.save(user);

        return user;
    }

    @Override
    public void delete(String userId) {

        Optional<User> userOp = repository.findById(userId);
        if (!userOp.isPresent()) {
            throw new RecordNotFoundException("No User record found for Id" , userId);
        }

    }

    @Override
    public UserDTO getUserById(String userId) {

        Optional<User> userOp = repository.findById(userId);

        if (!userOp.isPresent()) {
            throw new RecordNotFoundException("No User record found for Id" , userId);
        }

        User user = userOp.get();

        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getNames(),
                user.getSurnames(),
                user.getEmail(),
                user.getUsername(),
                user.getProfilePicture());

        return userDTO;
    }

    @Override
    public UserDTO getUserByName(String username) {

        User user = repository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid Username or Password");
        }

        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getNames(),
                user.getSurnames(),
                user.getEmail(),
                user.getUsername(),
                user.getProfilePicture());

        return userDTO;
    }

    @Override
    public List<UserDTO> getUsers(int pageIndex, int pageSize) {
        Page<User> usersPage = repository.findAll(PageRequest.of(pageIndex, pageSize));
        return usersPage.get().map(this::convertUserToDTO).collect(Collectors.toList());
    }

    private UserDTO convertUserToDTO(User user) {
        return new UserDTO(user.getId(), user.getNames(), user.getSurnames(), user.getEmail(), user.getPassword(), user.getProfilePicture());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid Username Or Password");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
