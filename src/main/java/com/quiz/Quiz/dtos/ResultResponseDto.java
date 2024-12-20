package com.quiz.Quiz.dtos;

import java.util.List;

public class ResultResponseDto {
    private UserDto user;
    private List<AnswerSummaryDetails> summaryDetailsList;
    private String category;
    private int numberOfQuestions;
    private int numberOfCorrectAnswer;
    private int numberOfInCorrectAnswer;
    private int numberOfNotAttemptedQuestions;
    private int score;

    public ResultResponseDto() {
    }

    public ResultResponseDto(String category,  int numberOfQuestions,int numberOfCorrectAnswer, int numberOfInCorrectAnswer, int numberOfNotAttemptedQuestions, int score, UserDto user,List<AnswerSummaryDetails> summaryDetailsList) {
        this.category = category;
        this.numberOfCorrectAnswer = numberOfCorrectAnswer;
        this.numberOfInCorrectAnswer = numberOfInCorrectAnswer;
        this.numberOfNotAttemptedQuestions = numberOfNotAttemptedQuestions;
        this.numberOfQuestions = numberOfQuestions;
        this.score = score;
        this.user = user;
        this.summaryDetailsList=summaryDetailsList;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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

    public int getNumberOfNotAttemptedQuestions() {
        return numberOfNotAttemptedQuestions;
    }

    public void setNumberOfNotAttemptedQuestions(int numberOfNotAttemptedQuestions) {
        this.numberOfNotAttemptedQuestions = numberOfNotAttemptedQuestions;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<AnswerSummaryDetails> getSummaryDetailsList() {
        return summaryDetailsList;
    }

    public void setSummaryDetailsList(List<AnswerSummaryDetails> summaryDetailsList) {
        this.summaryDetailsList = summaryDetailsList;
    }
}
