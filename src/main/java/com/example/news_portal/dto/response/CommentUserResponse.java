package com.example.news_portal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CommentUserResponse {
    private Long id;
    private String fullName;
}
