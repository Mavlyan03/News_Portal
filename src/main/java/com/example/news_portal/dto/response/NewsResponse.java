package com.example.news_portal.dto.response;

import com.example.news_portal.model.News;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class NewsResponse {
    private Long id;
    private String header;
    private String description;
    private String image;
    private LocalDate publicationDate;
    private Boolean selected;

    public NewsResponse(Long id, String header, String description, String image, LocalDate publicationDate) {
        this.id = id;
        this.header = header;
        this.description = description;
        this.image = image;
        this.publicationDate = publicationDate;
    }

    public NewsResponse(News news, Boolean selected) {
        this.id = news.getId();
        this.header = news.getHeader();
        this.description = news.getShortDescription();
        this.image = news.getNews_cover();
        this.publicationDate = news.getPublicationDate();
        this.selected = selected;
    }

    public NewsResponse(NewsResponse newsResponse, Boolean selected) {
        this.id = newsResponse.getId();
        this.header = newsResponse.getHeader();
        this.description = newsResponse.getDescription();
        this.image = newsResponse.getImage();
        this.publicationDate = newsResponse.getPublicationDate();
        this.selected = selected;
    }
}
