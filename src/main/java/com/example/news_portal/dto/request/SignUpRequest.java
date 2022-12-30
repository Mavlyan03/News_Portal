package com.example.news_portal.dto.request;

import com.example.news_portal.validation.PasswordValid;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequest {
    private String name;
    private String surname;
    private String nickname;
    @PasswordValid
    private String password;
    private String resetPassword;
}
