package com.example.news_portal.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class NewsResponse {
    private Long id;
    private String header;
    private String description;
    private String image;
    private LocalDate publicationDate;
}
