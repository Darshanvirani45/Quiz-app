package com.quiz.Quiz.entities;

import com.quiz.Quiz.enums.QuizStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="tbl_session")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="session_id")
    private long sessionId;

    @Column(name="number_of_questions")
    private int numberOfQuestions;

    @Column(name="number_of_corr_ans")
    private int numberOfCorrectAnswer;

    @Column(name="date")
    private LocalDate date;

    @Column(name="number_of_in_corr_ans")
    private int numberOfInCorrectAnswer;

    @Column(name="number_of_not_attempted_ans")
    private int numberOfNotAttemptedQuestion;

    @Column(name="score")
    private int score;

    @Column(name="quiz_status")
    @Enumerated(EnumType.STRING)
    private QuizStatus quizStatus;

    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private User user;

    @ManyToMany(mappedBy = "sessions",cascade = CascadeType.ALL)
    private List<Question> questions;

    @ManyToOne
    @JoinColumn(name = "category_id_fk")
    private QuizCategory category;

    @OneToMany(mappedBy = "session")
    private List<SessionQuestionAnswerHistory> histories;

    public Session() {
    }

    public Session(int numberOfQuestions, int numberOfCorrectAnswer, int numberOfInCorrectAnswer, int score, QuizStatus quizStatus, User user, List<Question> questions, QuizCategory category,int numberOfNotAttemptedQuestion,LocalDate date) {
        this.numberOfQuestions = numberOfQuestions;
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
        this.numberOfInCorrectAnswer = numberOfInCorrectAnswer;
        this.score = score;
        this.quizStatus = quizStatus;
        this.user = user;
        this.questions = questions;
        this.category = category;
        this.numberOfNotAttemptedQuestion=numberOfNotAttemptedQuestion;
        this.date=date;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getNumberOfCorrectAnswer() {
        return numberOfCorrectAnswer;
    }

    public void setNumberOfCorrectAnswer(int numberOfCorrectAnswer) {
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
    }

    public int getNumberOfInCorrectAnswer() {
        return numberOfInCorrectAnswer;
    }

    public void setNumberOfInCorrectAnswer(int numberOfInCorrectAnswer) {
        this.numberOfInCorrectAnswer = numberOfInCorrectAnswer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public QuizStatus getQuizStatus() {
        return quizStatus;
    }

    public void setQuizStatus(QuizStatus quizStatus) {
        this.quizStatus = quizStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public QuizCategory getCategory() {
        return category;
    }

    public void setCategory(QuizCategory category) {
        this.category = category;
    }

    public int getNumberOfNotAttemptedQuestion() {
        return numberOfNotAttemptedQuestion;
    }

    public void setNumberOfNotAttemptedQuestion(int numberOfNotAttemptedQuestion) {
        this.numberOfNotAttemptedQuestion = numberOfNotAttemptedQuestion;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
