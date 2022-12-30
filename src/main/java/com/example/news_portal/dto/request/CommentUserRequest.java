package com.example.news_portal.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUserRequest {
    private Long id;
    private String comment;
}
