package com.example.news_portal.dto.response;

import com.example.news_portal.db.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class AnswerResponse {
    private Long id;
    private String fullName;
    private String image;
    private String comment;
    private LocalDate dateOfComment;

    public AnswerResponse(Long id, User user, String answer) {
        this.id = id;
        this.fullName = user.getName() + " " + user.getSurname();
        this.image = user.getPhoto();
        this.comment = answer;
        this.dateOfComment = LocalDate.now();
    }

}
