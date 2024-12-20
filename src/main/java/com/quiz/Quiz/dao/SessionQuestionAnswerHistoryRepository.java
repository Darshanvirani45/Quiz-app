package com.quiz.Quiz.dao;

import com.quiz.Quiz.entities.SessionQuestionAnswerHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessionQuestionAnswerHistoryRepository extends JpaRepository<SessionQuestionAnswerHistory,Long> {
    List<SessionQuestionAnswerHistory> findBySession_SessionId(long categoryId);
}
