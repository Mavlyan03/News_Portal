package com.example.news_portal.service;

import com.example.news_portal.dto.request.NewsRequest;
import com.example.news_portal.dto.response.CommentResponse;
import com.example.news_portal.dto.response.NewsInnerPageResponse;
import com.example.news_portal.dto.response.NewsResponse;
import com.example.news_portal.dto.response.SimpleResponse;
import com.example.news_portal.model.Comment;
import com.example.news_portal.model.News;
import com.example.news_portal.model.User;
import com.example.news_portal.exception.NotFoundException;
import com.example.news_portal.repository.NewsRepository;
import com.example.news_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    public NewsResponse save(NewsRequest newsRequest) {
        News news = new News(newsRequest);
        news.setPublicationDate(LocalDate.now());
        User publisher = userRepository.findById(newsRequest.getPublisherId())
                .orElseThrow(() -> new NotFoundException("Publisher not found"));
        publisher.getMyPublications().add(news);
        news.setPublisher(publisher);
        newsRepository.save(news);
        return newsRepository.getNews(news.getId());
    }

    public SimpleResponse delete(Long id) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new NotFoundException("News not found"));
        User user = newsRepository.getUserByNewsId(news.getPublisher().getId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.getMyPublications().remove(news);
        news.setPublisher(null);
        news.setElected(null);
        newsRepository.delete(news);
        return new SimpleResponse("News deleted successfully");
    }

    public List<NewsResponse> getAllNews(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        User user1 = userRepository.findById(user.getId()).orElseThrow(
                () -> new NotFoundException("User not found"));
        return newsRepository.getAllNews(user1.getId());
    }

    public NewsResponse getById(Long id) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new NotFoundException("News not found"));
        return newsRepository.getNews(news.getId());
    }

    public NewsInnerPageResponse getNewsById(Long id) {
        News news = newsRepository.findById(id).orElseThrow(
                () -> new NotFoundException("News not found"));
        List<CommentResponse> comments = new ArrayList<>();
        for(Comment comment : news.getComments()) {
            comments.add(new CommentResponse(comment));
        }
        return null;
    }
}
