package com.example.news_portal.api;

import com.example.news_portal.dto.request.AnswerRequest;
import com.example.news_portal.dto.request.CommentRequest;
import com.example.news_portal.dto.response.NewsInnerPageResponse;
import com.example.news_portal.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("comment/api")
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Comment API", description = "Add comment to news")
@PreAuthorize("hasAuthority('USER')")
public class CommentApi {

    private final CommentService commentService;

    @PostMapping
    @Operation(summary = "Save comment",
            description = "User can leave a comment")
    public NewsInnerPageResponse comment(CommentRequest commentRequest) {
        return commentService.comment(commentRequest);
    }

//    @PostMapping("/answer")
//    @Operation(summary = "Answer to comment",
//            description = "User can answer to comment")
//    public NewsInnerPageResponse answerToComment(AnswerRequest answerRequest) {
//        return commentService.answerToComment(answerRequest);
//    }
}
