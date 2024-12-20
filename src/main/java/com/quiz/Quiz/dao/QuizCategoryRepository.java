package com.quiz.Quiz.dao;

import com.quiz.Quiz.entities.QuizCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizCategoryRepository extends JpaRepository<QuizCategory,Long> {
    QuizCategory findByCategoryName(String name);
}
