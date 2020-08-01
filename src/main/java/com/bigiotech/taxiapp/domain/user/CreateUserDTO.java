package com.bigiotech.taxiapp.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class CreateUserDTO implements Serializable {
    private final String names;
    private final String surnames;
    private final String email;
    private final String username;
    private final String password;
    private final String profilePicture;
}
