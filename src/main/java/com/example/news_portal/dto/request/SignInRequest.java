package com.example.news_portal.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NotBlank
public class SignInRequest {
    private String nickname;
    private String password;
}
