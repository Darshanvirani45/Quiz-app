package com.quiz.Quiz.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="tbl_question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private long questionId;

    @Column(length = 5000,name = "question_text")
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "quiz_category_id_fk")
    private QuizCategory quizCategory;

    @ManyToMany
    @JoinTable(
            name="tbl_join_questions_session",
            joinColumns = @JoinColumn(name = "question_id"),
            inverseJoinColumns = @JoinColumn(name="session_id-fk")
    )
    private List<Session> sessions;

    @OneToMany(mappedBy = "question",cascade = CascadeType.ALL)
    private List<Answer> answers;


    @OneToMany(mappedBy = "question")
    private List<SessionQuestionAnswerHistory> histories;

    public Question() {
    }

    public Question(String questionText, QuizCategory quizCategory,List<Answer> answers) {
        this.questionText = questionText;
        this.quizCategory = quizCategory;
        this.answers=answers;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public QuizCategory getQuizCategory() {
        return quizCategory;
    }

    public void setQuizCategory(QuizCategory quizCategory) {
        this.quizCategory = quizCategory;
    }


    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Question{" +
                "answers=" + answers +
                ", questionId=" + questionId +
                ", questionText='" + questionText + '\'' +
                ", quizCategory=" + quizCategory +
                ", sessions=" + sessions +
                '}';
    }
}
