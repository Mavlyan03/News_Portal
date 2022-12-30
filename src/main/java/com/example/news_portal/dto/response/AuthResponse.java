package com.example.news_portal.dto.response;

import com.example.news_portal.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class AuthResponse {
    private Long id;
    private String name;
    private String surname;
    private Role role;
    private String token;
}
