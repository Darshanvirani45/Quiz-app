package com.quiz.Quiz.dao;

import com.quiz.Quiz.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {

    Answer findByQuestion_QuestionIdAndIsCorrect(long questionId, boolean isCorrect);

    Answer findByQuestion_QuestionIdAndOption(long questionId, char option);
}
