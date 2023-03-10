package com.example.news_portal.db.repository;

import com.example.news_portal.db.model.News;
import com.example.news_portal.db.model.User;
import com.example.news_portal.dto.response.NewsInnerPageResponse;
import com.example.news_portal.dto.response.NewsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("select new com.example.news_portal.dto.response.NewsResponse(" +
            "n.id," +
            "n.header," +
            "n.shortDescription," +
            "n.news_cover," +
            "n.publicationDate) from News n where n.id = ?1")
    NewsResponse getNews(Long id);

    @Query("select new com.example.news_portal.dto.response.NewsInnerPageResponse(" +
            "n.id," +
            "n.header," +
            "n.shortDescription," +
            "n.textNews," +
            "n.news_cover," +
            "n.publicationDate) from News n where n.id = :id")
    NewsInnerPageResponse getNewsById(Long id);

    @Query("select n.publisher from News n where n.publisher.id = :id")
    Optional<User> getUserByNewsId(Long id);

    @Query("select new com.example.news_portal.dto.response.NewsResponse(" +
            "n.id," +
            "n.header," +
            "n.shortDescription," +
            "n.news_cover," +
            "n.publicationDate) from News n where n.publisher.id <> ?1")
    List<NewsResponse> getAllNews(Long id);

    @Query("select new com.example.news_portal.dto.response.NewsResponse(" +
            "n.id," +
            "n.header," +
            "n.shortDescription," +
            "n.news_cover," +
            "n.publicationDate) from News n where n.publisher.id = ?1")
    List<NewsResponse> getMyPublications(Long id);
}