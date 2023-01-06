package com.example.news_portal.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SelectRequest {
    private List<Long> newsId;
}
