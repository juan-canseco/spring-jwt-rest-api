package com.bigiotech.taxiapp.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class UserDTO implements Serializable {
    private final String id;
    private final String names;
    private final String surnames;
    private final String email;
    private final String username;
    private final String profilePicture;
}
