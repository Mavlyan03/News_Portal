package com.example.news_portal.api;

import com.example.news_portal.dto.request.SignInRequest;
import com.example.news_portal.dto.request.SignUpRequest;
import com.example.news_portal.dto.request.UpdateProfileRequest;
import com.example.news_portal.dto.response.AuthResponse;
import com.example.news_portal.dto.response.NewsResponse;
import com.example.news_portal.dto.response.UpdateProfileResponse;
import com.example.news_portal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@CrossOrigin(origins = "*",maxAge = 3600)
@Tag(name = "Auth API",description = "Sign Up and Sign In")
public class AuthApi {

    private final UserService userService;

    @PostMapping("/sing_up")
    @Operation(summary = "Sign Up",
            description = "User can sign up")
    public AuthResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return userService.registration(signUpRequest);
    }

    @PostMapping("/sign_in")
    @Operation(summary = "Sign In",description = "User can sig in")
    public AuthResponse signIn(@RequestBody SignInRequest signInRequest) {
        return userService.login(signInRequest);
    }

    @GetMapping("/my")
    @Operation(summary = "Get all my publications",
            description = "User get all publications")
    public List<NewsResponse> getMyPublications(Authentication authentication) {
        return userService.getMyPublications(authentication);
    }

    @PutMapping
    @Operation(summary = "Update profile",
            description = "User can update his profile")
    public UpdateProfileResponse update(@RequestBody UpdateProfileRequest request) {
        return userService.update(request);
    }
}
