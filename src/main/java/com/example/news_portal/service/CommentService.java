package com.example.news_portal.service;

import com.example.news_portal.dto.request.AnswerRequest;
import com.example.news_portal.dto.request.CommentRequest;
import com.example.news_portal.dto.response.AnswerResponse;
import com.example.news_portal.dto.response.CommentResponse;
import com.example.news_portal.dto.response.NewsInnerPageResponse;
import com.example.news_portal.exception.NotFoundException;
import com.example.news_portal.model.Answer;
import com.example.news_portal.model.Comment;
import com.example.news_portal.model.News;
import com.example.news_portal.model.User;
import com.example.news_portal.repository.CommentRepository;
import com.example.news_portal.repository.NewsRepository;
import com.example.news_portal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final UserRepository userRepository;

    private User getAuthentication() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String name = authentication.getName();
        return userRepository.findByName(name).orElseThrow(
                () -> new NotFoundException("User with name %s not found"));
    }

    public NewsInnerPageResponse comment(CommentRequest commentRequest) {
        News news = newsRepository.findById(commentRequest.getNewsId())
                .orElseThrow(() -> new NotFoundException("News not found"));
        Comment comment = new Comment(commentRequest);
        User user = getAuthentication();
        comment.setUser(user);
        user.getMyComments().add(comment);
        comment.setNews(news);
        news.getComments().add(comment);
        commentRepository.save(comment);
        NewsInnerPageResponse newsInnerPage = newsRepository.getNewsById(news.getId());
        List<CommentResponse> comments = commentRepository.getCommentsByNewsId(news.getId());
        newsInnerPage.setComments(comments);
        if (news.getSelect().contains(user)) {
            newsInnerPage.setSelected(true);
        } else {
            newsInnerPage.setSelected(false);
        }
        return newsInnerPage;
    }

    public NewsInnerPageResponse answerToComment(AnswerRequest answerRequest) {
        Comment comment = commentRepository.findById(answerRequest.getCommentId())
                .orElseThrow(() -> new NotFoundException("Comment not found"));
        News news1 = newsRepository.findById(comment.getNews().getId())
                .orElseThrow(() -> new NotFoundException("News not found"));
        User user = getAuthentication();
        Answer answer = new Answer(user, answerRequest);
        answer.setComment(comment);
        answer.setUser(user);
        comment.getAnswers().add(answer);
        commentRepository.save(comment);
        NewsInnerPageResponse news = newsRepository.getNewsById(news1.getId());
        List<CommentResponse> comments = commentRepository.getCommentsByNewsId(news.getId());
        for (CommentResponse commentResponse : comments) {
            if (commentResponse.getId().equals(comment.getId())) {
                commentResponse.getAnswers().add(
                        new AnswerResponse(
                                answer.getId(),
                                user,
                                answerRequest.getComment()));
            }
        }
        news.setComments(comments);
        if (news1.getSelect().contains(user)) {
            news.setSelected(true);
        } else {
            news.setSelected(false);
        }
        return news;
    }
}
