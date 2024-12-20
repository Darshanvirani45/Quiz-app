package com.quiz.Quiz.dtos;


public class CreateSessionResponse {
    private long sessionId;
    private int numberOfQuestions;
    private UserDto sessionUser;

    public CreateSessionResponse() {
    }

    public CreateSessionResponse(int numberOfQuestions, long sessionId, UserDto sessionUser) {
        this.numberOfQuestions = numberOfQuestions;
        this.sessionId = sessionId;
        this.sessionUser = sessionUser;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public void setNumberOfQuestions(int numberOfQuestions) {
        this.numberOfQuestions = numberOfQuestions;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }

    public UserDto getSessionUser() {
        return sessionUser;
    }

    public void setSessionUser(UserDto sessionUser) {
        this.sessionUser = sessionUser;
    }
}
