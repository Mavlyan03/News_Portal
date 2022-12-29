package com.example.news_portal.api;

import com.example.news_portal.dto.request.UpdateProfileRequest;
import com.example.news_portal.dto.response.NewsResponse;
import com.example.news_portal.dto.response.UpdateProfileResponse;
import com.example.news_portal.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/profile")
@CrossOrigin(origins = "*",maxAge = 3600)
@Tag(name = "Profile API",description = "Profile endpoints")
@PreAuthorize("hasAuthority('USER')")
public class ProfileApi {

    private final UserService userService;

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
