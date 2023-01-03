package com.example.news_portal.dto.response;

import com.example.news_portal.model.News;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class NewsInnerPageResponse {
    private Long id;
    private String header;
    private String description;
    private String text;
    private String image;
    private LocalDate publicationDate;
    private Boolean selected;
    private List<CommentResponse> comments;

    public NewsInnerPageResponse(Long id, String header, String description, String text, String image, LocalDate publicationDate) {
        this.id = id;
        this.header = header;
        this.description = description;
        this.text = text;
        this.image = image;
        this.publicationDate = publicationDate;
    }

    public NewsInnerPageResponse(News news) {
        this.id = news.getId();
        this.header = news.getHeader();
        this.description = news.getShortDescription();
        this.text = news.getTextNews();
        this.image = news.getNews_cover();
        this.publicationDate = news.getPublicationDate();
    }
}
