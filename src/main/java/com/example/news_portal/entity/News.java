package com.example.news_portal.entity;

import com.example.news_portal.entity.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Table(name = "news")
@Getter
@Setter
@NoArgsConstructor
public class News {
    @Id
    @SequenceGenerator(name = "news_seq", sequenceName = "news_seq", allocationSize = 1, initialValue = 10)
    @GeneratedValue(generator = "news_seq", strategy = GenerationType.SEQUENCE)
    private Long id;
    private String news_cover;
    private String header;
    private String shortDescription;
    private String textNews;
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToMany(cascade = {
            REFRESH,
            MERGE,
            DETACH})
    private List<User> elected;
}
