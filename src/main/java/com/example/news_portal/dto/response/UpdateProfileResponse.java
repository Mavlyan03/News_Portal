package com.example.news_portal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateProfileResponse {
    private Long id;
    private String name;
    private String surname;
    private String nickname;
    private String photo;
}
