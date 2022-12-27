package com.example.news_portal.service;

import com.example.news_portal.dto.request.NewsRequest;
import com.example.news_portal.dto.response.NewsResponse;
import com.example.news_portal.entity.News;
import com.example.news_portal.entity.User;
import com.example.news_portal.exception.NotFoundException;
import com.example.news_portal.repository.NewsRepository;
import com.example.news_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public NewsResponse save(NewsRequest newsRequest) {
        News news = new News(newsRequest);
        User publisher = userRepository.findById(newsRequest.getPublisherId())
                .orElseThrow(() -> new NotFoundException("Publisher not found"));
        publisher.getMyPublications().add(news);
        news.setPublisher(publisher);
        newsRepository.save(news);
        return newsRepository.getNews(news.getId());
    }

    public NewsResponse delete(Long id) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new NotFoundException("News not found"));
        User user = newsRepository.getUserByNewsId(news.getPublisher().getId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        return null;
    }
}
