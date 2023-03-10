package com.example.news_portal.db.model;

import com.example.news_portal.db.model.enums.Category;
import com.example.news_portal.dto.request.NewsRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.CascadeType.*;


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

    @OneToMany(cascade = ALL, mappedBy = "news")
    private List<Comment> comments;

    @ManyToMany(cascade = {
            PERSIST,
            REFRESH,
            MERGE,
            DETACH}, mappedBy = "favorites")
    private List<User> select;

    @ManyToOne(cascade = {
            REFRESH,
            MERGE,
            DETACH})
    private User publisher;

    public News(NewsRequest request) {
        this.header = request.getHeader();
        this.shortDescription = request.getDescription();
        this.textNews = request.getText();
        this.news_cover = request.getImage();
        this.category = request.getCategory();
    }
}
