package com.example.news_portal.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerRequest {
    private Long commentId;
    private String comment;
}
