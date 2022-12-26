package com.example.news_portal.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String name;
    private String surname;
    private String nickname;
    private String password;
    private String resetPassword;
}
