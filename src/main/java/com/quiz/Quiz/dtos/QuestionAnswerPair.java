package com.quiz.Quiz.dtos;

import java.util.TreeMap;

public class QuestionAnswerPair {
    private String questionText;
    private TreeMap<Character,String> optionWithAnswer;

    public QuestionAnswerPair() {
    }

    public QuestionAnswerPair(String questionText,TreeMap<Character,String> optionWithAnswer) {
        this.optionWithAnswer = optionWithAnswer;
        this.questionText = questionText;
    }

    public TreeMap<Character,String> getAnswerOptionPairs() {
        return optionWithAnswer;
    }

    public void setAnswerOptionPairs(TreeMap<Character,String> optionWithAnswer) {
        this.optionWithAnswer = optionWithAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }
}
