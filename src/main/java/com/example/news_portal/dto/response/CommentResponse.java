package com.example.news_portal.dto.response;

import com.example.news_portal.model.Comment;
import com.example.news_portal.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CommentResponse {
    private Long id;
    private String fullName;
    private String image;
    private String comment;
    private LocalDate commentedAt;
    private List<AnswerResponse> answers;

    public CommentResponse(Comment comment, User user) {
        this.id = comment.getId();
        this.fullName = user.getName() + " " + user.getSurname();
        this.comment = comment.getComment();
        this.commentedAt = comment.getDateOfComment();
    }
}
