package com.example.news_portal.entity;

import com.example.news_portal.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "user_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String surname;
    private String nickname;
    private String photo;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(cascade = ALL,mappedBy = "publisher")
    private List<News> myPublications;

    @ManyToMany(cascade = {
            REFRESH,
            MERGE,
            DETACH})
    private List<News> favorites;

    @ManyToMany(cascade = {
            REFRESH,
            MERGE,
            DETACH}, mappedBy = "users")
    private List<Comment> myComments;
}
