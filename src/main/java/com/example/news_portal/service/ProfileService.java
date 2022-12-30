package com.example.news_portal.service;

import com.example.news_portal.dto.request.UpdateProfileRequest;
import com.example.news_portal.dto.response.NewsResponse;
import com.example.news_portal.dto.response.UpdateProfileResponse;
import com.example.news_portal.model.User;
import com.example.news_portal.exception.NotFoundException;
import com.example.news_portal.repository.NewsRepository;
import com.example.news_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

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
//        userRepository.updateProfile(
//                request.getId(),
//                request.getName(),
//                request.getSurname(),
//                request.getNickname(),
//                request.getPhoto());
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
        return newsRepository.getMyPublications(user1.getId());
    }
}
