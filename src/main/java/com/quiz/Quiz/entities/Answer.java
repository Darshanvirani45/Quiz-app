package com.quiz.Quiz.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_answer")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private long answerId;

    @Column(length = 1000,name = "answer_text")
    private String answerText;

    @Column(name = "is_correct")
    private boolean isCorrect;

    @Column(name="col_option")
    private char option;

    @ManyToOne
    @JoinColumn(name = "question_id_fk")
    private Question question;


    public Answer() {
    }

    public Answer(String answerText, boolean isCorrect,char option,Question question) {
        this.answerText = answerText;
        this.isCorrect = isCorrect;
        this.question = question;
        this.option=option;
    }

    public long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(long answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public char getOption() {
        return option;
    }

    public void setOption(char option) {
        this.option = option;
    }
}
