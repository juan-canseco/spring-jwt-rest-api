package com.bigiotech.taxiapp.domain.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class TokenDTO implements Serializable {

    private final String token;

    @JsonProperty("user")
    private final UserDTO userDTO;
}
