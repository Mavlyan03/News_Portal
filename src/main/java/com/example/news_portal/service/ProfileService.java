package com.example.news_portal.service;

import com.example.news_portal.dto.request.UpdateProfileRequest;
import com.example.news_portal.dto.response.NewsResponse;
import com.example.news_portal.dto.response.UpdateProfileResponse;
import com.example.news_portal.model.News;
import com.example.news_portal.model.User;
import com.example.news_portal.exception.NotFoundException;
import com.example.news_portal.repository.NewsRepository;
import com.example.news_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final NewsRepository newsRepository;

    public UpdateProfileResponse update(UpdateProfileRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(
                () -> new NotFoundException("User not found"));
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setNickname(request.getNickname());
        user.setPhoto(request.getPhoto());
        userRepository.save(user);
        return new UpdateProfileResponse(
                user.getId(),
                request.getName(),
                request.getSurname(),
                request.getNickname(),
                request.getPhoto());
    }

    public List<NewsResponse> getMyPublications(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        User user1 = userRepository.findById(user.getId()).orElseThrow(
                () -> new NotFoundException("User not found"));
        List<NewsResponse> myPublications = newsRepository.getMyPublications(user1.getId());
        List<NewsResponse> myFavorites = new ArrayList<>();
        for(NewsResponse newsResponse : myPublications) {
            News news = newsRepository.findById(newsResponse.getId()).orElseThrow(
                    () -> new NotFoundException("News not found"));
            if(news.getSelect().contains(user1)) {
                myFavorites.add(new NewsResponse(newsResponse, true));
            } else {
                myFavorites.add(new NewsResponse(newsResponse, false));
            }
        }
        return myFavorites;
    }
}
