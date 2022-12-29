package com.example.news_portal.service;

import com.example.news_portal.dto.request.UpdateProfileRequest;
import com.example.news_portal.dto.response.AuthResponse;
import com.example.news_portal.entity.User;
import com.example.news_portal.exception.NotFoundException;
import com.example.news_portal.repository.NewsRepository;
import com.example.news_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final NewsRepository newsRepository;

    public AuthResponse update(UpdateProfileRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(
                () -> new NotFoundException("User not found"));
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setNickname(request.getNickname());
        user.setPhoto(request.getPhoto());
        return null;
    }
}
