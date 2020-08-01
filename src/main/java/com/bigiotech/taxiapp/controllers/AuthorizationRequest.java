package com.bigiotech.taxiapp.controllers;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Getter
@AllArgsConstructor
@Builder
public class AuthorizationRequest implements Serializable {

    @JsonProperty("user")
    private String username;
    private String password;

    public AuthorizationRequest() {

    }
}
