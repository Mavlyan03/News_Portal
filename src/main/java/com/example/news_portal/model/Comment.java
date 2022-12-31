package com.example.news_portal.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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
}
