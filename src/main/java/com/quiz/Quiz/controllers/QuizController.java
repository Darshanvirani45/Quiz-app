package com.quiz.Quiz.controllers;

import com.quiz.Quiz.dtos.CreateSessionRequestDto;
import com.quiz.Quiz.dtos.SubmitAnswerRequestDto;
import com.quiz.Quiz.services.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    private SessionService sessionService;


    //this endpoint create new session for quiz
    @PostMapping("/session")
    public ResponseEntity<?> createSession(@RequestBody CreateSessionRequestDto createSessionRequestDto){
        return sessionService.createSession(createSessionRequestDto);
    }

    //this endpoint will give us a set of random question
    @GetMapping("/random-questions")
    public ResponseEntity<?> getRandomQuestions(@RequestHeader long sessionId){
        return sessionService.getSessionQuestions(sessionId);
    }

    //this endpoint will submit and verify all the provided answer
    @PostMapping("/submit-answer")
    public ResponseEntity<?> submitAnswer(@RequestBody SubmitAnswerRequestDto submitAnswerRequestDto){
        return sessionService.verifyAnswers(submitAnswerRequestDto);
    }

    //this endpoint will give us a set of random question
    @GetMapping("/result")
    public ResponseEntity<?> getResult(@RequestHeader long sessionId){
        return sessionService.getResult(sessionId);
    }
}
