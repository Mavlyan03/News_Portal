package com.example.news_portal.api;

import com.example.news_portal.dto.request.SignInRequest;
import com.example.news_portal.dto.request.SignUpRequest;
import com.example.news_portal.dto.response.AuthResponse;
import com.example.news_portal.db.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Auth API", description = "Sign Up and Sign In")
public class AuthApi {

    private final UserService userService;

    @PostMapping("/sing_up")
    @Operation(summary = "Sign Up",
            description = "User can sign up")
    public AuthResponse signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        return userService.registration(signUpRequest);
    }

    @PostMapping("/sign_in")
    @Operation(summary = "Sign In", description = "User can sig in")
    public AuthResponse signIn(@RequestBody SignInRequest signInRequest) {
        return userService.login(signInRequest);
    }
}
