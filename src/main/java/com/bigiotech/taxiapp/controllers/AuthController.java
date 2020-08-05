package com.bigiotech.taxiapp.controllers;

import com.bigiotech.taxiapp.domain.user.CreateUserDTO;
import com.bigiotech.taxiapp.domain.user.UserDTO;
import com.bigiotech.taxiapp.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class AuthController {

    private UserService service;

    @Autowired
    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity register(@RequestBody CreateUserDTO dto) {
        service.register(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable String userId) {
        UserDTO user = service.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/profiles")
    public ResponseEntity<List<UserDTO>> getProfiles(int pageIndex, int pageSize) {
        List<UserDTO> users = service.getUsers(pageIndex, pageSize);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
