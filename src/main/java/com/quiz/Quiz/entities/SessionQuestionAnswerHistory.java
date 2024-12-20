package com.quiz.Quiz.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tbl_question_answer_history")
public class SessionQuestionAnswerHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private long historyId;

    @ManyToOne
    @JoinColumn(name = "question_id_fk")
    private Question question;

    @Column(name="col_option")
    private Character option;

    @ManyToOne
    @JoinColumn(name="session_id_fk")
    private Session session;

    public SessionQuestionAnswerHistory() {
    }

    public SessionQuestionAnswerHistory( Character option, Question question,Session session) {
        this.option = option;
        this.session=session;
        this.question = question;
    }

    public long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(long historyId) {
        this.historyId = historyId;
    }

    public Character getOption() {
        return option;
    }

    public void setOption(Character option) {
        this.option = option;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
}
