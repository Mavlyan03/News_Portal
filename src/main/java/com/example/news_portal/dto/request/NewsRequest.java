package com.example.news_portal.dto.request;

import com.example.news_portal.db.model.enums.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsRequest {
    private String header;
    private String description;
    private String text;
    private String image;
    private Category category;
}
