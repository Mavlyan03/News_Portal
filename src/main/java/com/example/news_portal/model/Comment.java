package com.example.news_portal.model;


import com.example.news_portal.dto.request.CommentRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;


@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @SequenceGenerator(name = "comment_seq", sequenceName = "comment_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "comment_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String comment;
    private LocalDate dateOfComment;

    @ManyToOne(cascade = {
            REFRESH,
            MERGE,
            DETACH})
    private User user;

    @ManyToOne(cascade = {
            REFRESH,
            MERGE,
            DETACH})
    private News news;

    @OneToMany(cascade = ALL, mappedBy = "comment")
    private List<Answer> answers = new ArrayList<>();

    public Comment(CommentRequest commentRequest) {
        this.comment = commentRequest.getComment();
        this.dateOfComment = LocalDate.now();
    }

    public Comment(String comment, LocalDate date) {
        this.comment = comment;
        this.dateOfComment = date;
    }
}
