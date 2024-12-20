package com.quiz.Quiz.dtos;

public class UserDto {
    private long userId;
    private String name;
    private String email;

    public UserDto() {
    }

    public UserDto(String email, String name, long userId) {
        this.email = email;
        this.name = name;
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
