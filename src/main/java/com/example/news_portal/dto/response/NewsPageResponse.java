package com.example.news_portal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NewsPageResponse {
    private Long id;
    private String header;
    private String description;
    private String text;
    private String image;
    private LocalDate publicationDate;
    private List<CommentResponse> comments;
}
