package com.example.news_portal.dto.response;

import com.example.news_portal.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String fullName;
    private String comment;
    private LocalDate commentedAt;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.commentedAt = comment.getDateOfComment();
    }

}
