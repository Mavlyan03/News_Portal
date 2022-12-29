package com.example.news_portal.service;

import com.example.news_portal.dto.request.SignInRequest;
import com.example.news_portal.dto.request.SignUpRequest;
import com.example.news_portal.dto.request.UpdateProfileRequest;
import com.example.news_portal.dto.response.AuthResponse;
import com.example.news_portal.dto.response.NewsResponse;
import com.example.news_portal.dto.response.UpdateProfileResponse;
import com.example.news_portal.entity.User;
import com.example.news_portal.entity.enums.Role;
import com.example.news_portal.exception.BadRequestException;
import com.example.news_portal.exception.NotFoundException;
import com.example.news_portal.repository.NewsRepository;
import com.example.news_portal.repository.UserRepository;
import com.example.news_portal.security.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final NewsRepository newsRepository;

    public AuthResponse registration(SignUpRequest signUpRequest) {
        User user = convertToRegisterEntity(signUpRequest);

        if (signUpRequest.getResetPassword().equals(signUpRequest.getPassword())) {
            user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            user.setRole(Role.USER);
            userRepository.save(user);
        } else {
            throw new BadRequestException("Password entered incorrectly");
        }

        String jwt = jwtTokenUtil.generateToken(user.getName());
        return new AuthResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getRole(),
                jwt);
    }

    public AuthResponse login(SignInRequest signInRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getNickname(),
                        signInRequest.getPassword()));

        User user = userRepository.findByName(authenticate.getName())
                .orElseThrow(() -> {
                    throw new NotFoundException("User not found");
                });

        String token = jwtTokenUtil.generateToken(user.getNickname());

        return new AuthResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getRole(),
                token);
    }

    private User convertToRegisterEntity(SignUpRequest request) {
        return User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .nickname(request.getNickname())
                .build();
    }

    public UpdateProfileResponse update(UpdateProfileRequest request) {
        User user = userRepository.findById(request.getId()).orElseThrow(
                () -> new NotFoundException("User not found"));
        userRepository.updateProfile(
                request.getId(),
                request.getName(),
                request.getSurname(),
                request.getNickname(),
                request.getPhoto());
        return new UpdateProfileResponse(
                user.getId(),
                user.getName(),
                user.getSurname(),
                user.getNickname(),
                user.getPhoto());
    }

    public List<NewsResponse> getMyPublications(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        User user1 = userRepository.findById(user.getId()).orElseThrow(
                () -> new NotFoundException("User not found"));
        return newsRepository.getMyPublications(user1.getId());
    }

}
