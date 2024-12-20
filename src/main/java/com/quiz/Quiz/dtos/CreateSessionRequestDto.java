package com.quiz.Quiz.dtos;

public class CreateSessionRequestDto {
    private long userId;
    private int totalQuestions;
    private String category;

    public CreateSessionRequestDto() {
    }

    public CreateSessionRequestDto(String category, int totalQuestions, long userId) {
        this.category = category;
        this.totalQuestions = totalQuestions;
        this.userId = userId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
