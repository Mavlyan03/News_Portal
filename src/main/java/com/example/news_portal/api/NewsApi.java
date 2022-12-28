package com.example.news_portal.api;

import com.example.news_portal.dto.request.NewsRequest;
import com.example.news_portal.dto.response.NewsResponse;
import com.example.news_portal.dto.response.SimpleResponse;
import com.example.news_portal.service.NewsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/news")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "News API", description = "USER news endpoints")
@PreAuthorize("hasAuthority('USER')")
public class NewsApi {

    private final NewsService newsService;

    @PostMapping
    @Operation(summary = "Save news",
            description = "To save a news by user")
    public NewsResponse save(@RequestBody NewsRequest newsRequest) {
        return newsService.save(newsRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete news",
            description = "Delete a news by id")
    public SimpleResponse delete(@PathVariable("id") Long id) {
        return newsService.delete(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get by id",
            description = "Get a news by id")
    public NewsResponse getById(@PathVariable Long id) {
        return newsService.getById(id);
    }

    @GetMapping
    @Operation(summary = "Get all news",
            description = "Get all news")
    public List<NewsResponse> getAllNews(Authentication authentication) {
        return newsService.getAllNews(authentication);
    }

    @GetMapping("/my")
    @Operation(summary = "Get all my publications",
            description = "User get all publications")
    public List<NewsResponse> getMyPublications(Authentication authentication) {
        return newsService.getMyPublications(authentication);
    }
}
