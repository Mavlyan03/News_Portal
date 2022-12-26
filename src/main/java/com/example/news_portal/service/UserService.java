package com.example.news_portal.service;

import com.example.news_portal.dto.request.SignUpRequest;
import com.example.news_portal.dto.response.AuthResponse;
import com.example.news_portal.entity.User;
import com.example.news_portal.entity.enums.Role;
import com.example.news_portal.exception.BadRequestException;
import com.example.news_portal.repository.UserRepository;
import com.example.news_portal.security.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

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
                user.getRole(), jwt);
    }

    private User convertToRegisterEntity(SignUpRequest request) {
        return User.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .nickname(request.getNickname())
                .build();
    }
}
