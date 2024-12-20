package com.quiz.Quiz.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tbl_quiz_category")
public class QuizCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private long categoryId;

    @Column(name="category_name")
    private String categoryName;

    @OneToMany(mappedBy = "quizCategory",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Question> questions;


    @OneToMany(mappedBy = "category")
    private List<Session> sessions;

    public QuizCategory() {
    }

    public QuizCategory(String categoryName, List<Question> questions) {
        this.categoryName = categoryName;
        this.questions = questions;
    }

    public QuizCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }


    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
}
