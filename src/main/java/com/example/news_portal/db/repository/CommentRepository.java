package com.example.news_portal.db.repository;

import com.example.news_portal.db.model.Comment;
import com.example.news_portal.dto.response.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select new com.example.news_portal.dto.response.CommentResponse(" +
            "c.id," +
            "concat(c.user.name,' ',c.user.surname)," +
            "c.user.photo,"+
            "c.comment," +
            "c.dateOfComment) from Comment c where c.news.id = :id")
    List<CommentResponse> getCommentsByNewsId(Long id);
}