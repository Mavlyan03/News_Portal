package com.example.news_portal.dto.request;

import com.example.news_portal.model.News;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SelectRequest {
    private List<Long> newsId;
}
