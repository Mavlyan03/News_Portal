package com.example.news_portal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.sasl.AuthenticationException;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class BadCredentialsException extends AuthenticationException {

    public BadCredentialsException(String detail) {
        super(detail);
    }
}
