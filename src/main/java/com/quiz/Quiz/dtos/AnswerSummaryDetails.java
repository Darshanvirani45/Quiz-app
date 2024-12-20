package com.quiz.Quiz.dtos;

public class AnswerSummaryDetails {
    private String question;
    private String selected;
    private String correct;

    public AnswerSummaryDetails() {
    }

    public AnswerSummaryDetails(String correct, String question, String selected) {
        this.correct = correct;
        this.question = question;
        this.selected = selected;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
}
