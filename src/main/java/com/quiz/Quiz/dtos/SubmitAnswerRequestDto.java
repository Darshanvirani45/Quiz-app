package com.quiz.Quiz.dtos;

import java.util.List;

public class SubmitAnswerRequestDto {
    private long sessionId;
    private List<Character> answerList;

    public SubmitAnswerRequestDto() {
    }

    public SubmitAnswerRequestDto(List<Character> answerList, long sessionId) {
        this.answerList = answerList;
        this.sessionId = sessionId;
    }

    public List<Character> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Character> answerList) {
        this.answerList = answerList;
    }

    public long getSessionId() {
        return sessionId;
    }

    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
}
