package com.example.news_portal.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "answers")
@Getter
@Setter
@NoArgsConstructor
public class Answer {
    @Id
    @SequenceGenerator(name = "answer_gen", sequenceName = "answer_gen", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "answer_gen", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fullName;
    private String photo;
    private String answer;
    private LocalDate dateOfComment;

    @ManyToOne(cascade = {
            REFRESH,
            MERGE,
            DETACH})
    private Comment comment;

    @ManyToOne(cascade = {
            REFRESH,
            MERGE,
            DETACH})
    private User user;

}
