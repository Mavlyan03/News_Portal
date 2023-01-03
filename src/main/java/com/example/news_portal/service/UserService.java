package com.example.news_portal.service;

import com.example.news_portal.dto.request.SignInRequest;
import com.example.news_portal.dto.request.SignUpRequest;
import com.example.news_portal.dto.response.AuthResponse;
import com.example.news_portal.model.User;
import com.example.news_portal.model.enums.Role;
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

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

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

}
