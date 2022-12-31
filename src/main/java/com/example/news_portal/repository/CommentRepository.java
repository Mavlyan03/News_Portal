package com.example.news_portal.repository;

import com.example.news_portal.dto.response.CommentResponse;
import com.example.news_portal.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select new com.example.news_portal.dto.response.CommentResponse(" +
            "c.id," +
            "concat(c.user.name,' ',c.user.surname)," +
            "c.comment," +
            "c.dateOfComment) from Comment c where c.news.id = :id")
    List<CommentResponse> getCommentsByNewsId(Long id);
}