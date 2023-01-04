package com.example.news_portal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AnswerResponse {
    private Long id;
    private String fullName;
    private String image;
    private String comment;
    private LocalDate commentedAt;

}
