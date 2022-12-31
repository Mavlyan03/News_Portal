package com.example.news_portal.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequest {
    private Long newsId;
    private String comment;
}
