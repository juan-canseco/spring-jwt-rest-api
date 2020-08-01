package com.bigiotech.taxiapp.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongCredentialsException extends RuntimeException {

    public WrongCredentialsException() {
        super("Invalid username or password.");
    }

}
