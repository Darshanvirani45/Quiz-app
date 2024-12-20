package com.quiz.Quiz.services;

import com.quiz.Quiz.dao.*;
import com.quiz.Quiz.dtos.*;
import com.quiz.Quiz.enums.QuizStatus;
import com.quiz.Quiz.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class SessionService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private QuestionRepository questionRepository;


    @Autowired
    private AnswerRepository answerRepository;


    @Autowired
    private SessionQuestionAnswerHistoryRepository historyRepository;

    private final static int MAXIMUM_QUESTION_LIMIT = 20;

    @Autowired
    private QuizCategoryRepository quizCategoryRepository;

    public ResponseEntity<?> createSession(CreateSessionRequestDto createSessionRequestDto) {
        try {
            //get total number of questions from createSessionRequestDto
            int totalQuestion = createSessionRequestDto.getTotalQuestions();
            //this check will ensure that number question are not greater then available questions
            if (MAXIMUM_QUESTION_LIMIT < totalQuestion) {
                return new ResponseEntity<>("Maximum limit is 20", HttpStatus.BAD_REQUEST);
            }
            //we get user from userId which is present in createSessionRequestDto
            User user = userRepository.findById(createSessionRequestDto.getUserId()).get();

            //we get category based on category name  which is present in createSessionRequestDto
            QuizCategory category = quizCategoryRepository.findByCategoryName(createSessionRequestDto.getCategory());

            //create a new session
            Session session = new Session();

            //achieve by direction relation between category and session entity
            session.setCategory(category);
            category.getSessions().add(session);

            //in starting quiz status is IN_PROCESS
            session.setQuizStatus(QuizStatus.IN_PROCESS);

            //achieve by direction relation between user and session entity
            session.setUser(user);
            user.getSessions().add(session);

            //setting quiz session date
            session.setDate(LocalDate.now());

            //set number of total question in this quiz session
            session.setNumberOfQuestions(totalQuestion);

            //get random questions list for this session
            List<Question> questions = getRandomQuestions(category, totalQuestion, session);

            //setting this random question list into session
            session.setQuestions(questions);

            //save session into table
            sessionRepository.save(session);

            //to modify user we again save user so that this session will add in session list in user record
            userRepository.save(user);

            //to modify category we again save category so that this session will add in session list in category record
            quizCategoryRepository.save(category);

            //generate response based on session
            return new ResponseEntity<>(createResponse(session), HttpStatus.CREATED);
        } catch (NoSuchElementException e) {
            //return this response if user not available
            return new ResponseEntity<>("No Such User Present", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            //if any internal exception occur
            return new ResponseEntity<>("Internal Server Exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private CreateSessionResponse createResponse(Session session) {
        //get user based from session
        User user = session.getUser();

        //based on this user we will create userDto object
        UserDto sessionUser = new UserDto(user.getEmail(), user.getName(), user.getUserId());

        //create session response object
        CreateSessionResponse response = new CreateSessionResponse();

        //set userDto's object
        response.setSessionUser(sessionUser);

        //set number of question
        response.setNumberOfQuestions(session.getNumberOfQuestions());

        //also set session id which used for further processing such for getting random questions,submitting answer
        response.setSessionId(session.getSessionId());

        return response;
    }

    private List<Question> getRandomQuestions(QuizCategory category, int totalQuestions, Session session) {
        //get all the question based on quiz category
        List<Question> questions = category.getQuestions();

        //create list where all the random questions are stored
        List<Question> randomQuestions = new ArrayList<>();

        //create an random class object for getting random index
        Random random = new Random();

        //loop will iterate till i reach's to totalQuestions so that we can get total number of  random Questions
        for (int i = 1; i <= totalQuestions; i++) {

            //get random index which is between 0 to question.size()
            int index = random.nextInt(questions.size());

            //removing that question from questions list ensure that no question can repeat which does not
            // led to conflict
            Question remove = questions.remove(index);

            //setting that  this question is taken in current passed session
            remove.getSessions().add(session);

            //add that question into random question list
            randomQuestions.add(remove);

        }
        //at last return that list of random question
        return randomQuestions;
    }

    public ResponseEntity<?> getSessionQuestions(long sessionId) {
        try {
            //get session based on passed session id
            Session session = sessionRepository.findById(sessionId).get();

            //create a list to store question answer pair
            List<QuestionAnswerPair> questionAnswerPairs = new ArrayList<>();

            //iterate loop over all the questions from the session
            for (Question question : session.getQuestions()) {

                //get all the answers of the current question
                List<Answer> answers = question.getAnswers();

                //make map for storing answer text with option
                TreeMap<Character, String> optionWithAnswer = new TreeMap<>();
                for (int i = 0; i < answers.size(); i++) {
                    optionWithAnswer.put(answers.get(i).getOption(), answers.get(i).getAnswerText());
                }

                //create an object of question-answer-pair
                QuestionAnswerPair questionAnswerPair = new QuestionAnswerPair(question.getQuestionText(), optionWithAnswer);

                //and add it into question-answer-list
                questionAnswerPairs.add(questionAnswerPair);
            }

            //return response object
            return new ResponseEntity<>(questionAnswerPairs, HttpStatus.OK);
        } catch (NoSuchElementException e) {

            //return this response when there no session present of mention sessionId
            return new ResponseEntity<>("No such Session Created", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<?> verifyAnswers(SubmitAnswerRequestDto submitAnswerRequestDto) {
        try {
            //get session based on passed session id which was present in submitAnswerRequestDto
            Session session = sessionRepository.findById(submitAnswerRequestDto.getSessionId()).get();

            //if user again try to submit then this response will return
            if (session.getQuizStatus().equals(QuizStatus.COMPLETED)) {
                return new ResponseEntity<>("Your Response is already submitted", HttpStatus.ALREADY_REPORTED);
            }

            //get all the question list of the mention session
            List<Question> questions = session.getQuestions();

            //get all the user which submitted by user
            List<Character> answerList = submitAnswerRequestDto.getAnswerList();

            //create list of summary details so that user can aware that what was the correct and what was the wrong answer
            List<AnswerSummaryDetails> summaryDetails = new ArrayList<>();

            //create local variable to count number of correct,incorrect,notAttempted
            int correct = 0, incorrect = 0, notAttempted = 0;

            //iterate over all the to verify answer
            for (int i = 0; i < questions.size(); i++) {

                //get answer of current question which is select by user
                Character answerOption = answerList.get(i);

                //create AnswerSummaryDetails object
                AnswerSummaryDetails answerSummary = new AnswerSummaryDetails();

                //get correct  Answer object based on question_id
                Answer correctAnswer = answerRepository.findByQuestion_QuestionIdAndIsCorrect(questions.get(i).getQuestionId(), true);

                //set the correct answer to answerSummary
                answerSummary.setCorrect(correctAnswer.getOption() + ") " + correctAnswer.getAnswerText());

                //set the question-text to answerSummary
                answerSummary.setQuestion(questions.get(i).getQuestionText());

                //if answerOption is null than that  means that user don't attempted this question
                if (answerOption == null) {

                    //set selected answer to none if answerOption is null
                    answerSummary.setSelected("none");

                    //increase notAttempted
                    notAttempted++;
                } else {

                    //get user  Answer object based on question_id and option which is selected by user
                    Answer userAnswer = answerRepository.findByQuestion_QuestionIdAndOption(questions.get(i).getQuestionId(), answerOption);

                    //set selected to answer-text with option
                    answerSummary.setSelected(answerOption + ") " + userAnswer.getAnswerText());

                    //if correct-answer and answer-option are equal then
                    if (correctAnswer.getOption() == answerOption) {

                        //increase correct
                        correct++;
                    } else {

                        //decrease correct
                        incorrect++;
                    }
                }

                //add this summary object to summaryDetails list
                summaryDetails.add(answerSummary);

                //create history of this question
                historyRepository.save(new SessionQuestionAnswerHistory(answerOption, questions.get(i), session));
            }

            //set number-of-correct question
            session.setNumberOfCorrectAnswer(correct);

            //set number-of-in-correct question
            session.setNumberOfInCorrectAnswer(incorrect);

            //set number-of-not-attempted-correct question
            session.setNumberOfNotAttemptedQuestion(notAttempted);

            //set score
            session.setScore(correct);

            //and then set status to COMPLETED
            session.setQuizStatus(QuizStatus.COMPLETED);

            //to apply this changes save session again
            sessionRepository.save(session);

            //and at last return response entity object
            return new ResponseEntity<>(createResponseOfResult(session, summaryDetails), HttpStatus.OK);
        } catch (NoSuchElementException e) {

            //return this response when there no session present of mention sessionId
            return new ResponseEntity<>("Not Such Session Present", HttpStatus.NOT_FOUND);
        }
    }

    private ResultResponseDto createResponseOfResult(Session session, List<AnswerSummaryDetails> summaryDetails) {
        //get user from session
        User user = session.getUser();

        //create userDto based on details available in user object
        UserDto userDto = new UserDto(user.getEmail(), user.getName(), user.getUserId());

        //at last return ResultResponseDto
        return
                new ResultResponseDto(session.getCategory().getCategoryName(),
                        session.getNumberOfQuestions(),
                        session.getNumberOfCorrectAnswer(),
                        session.getNumberOfInCorrectAnswer(),
                        session.getNumberOfNotAttemptedQuestion(),
                        session.getScore(),
                        userDto,
                        summaryDetails);
    }

    public ResponseEntity<?> getResult(long sessionId) {

        try {
            //get all the history of mention session id
            List<SessionQuestionAnswerHistory> histories = historyRepository.findBySession_SessionId(sessionId);

            //get session based on session id
            Session session = sessionRepository.findById(sessionId).get();

            //create list of summary details
            List<AnswerSummaryDetails> summaryDetails = new ArrayList<>();

            //iterate over histories
            for (SessionQuestionAnswerHistory history : histories) {

                //get question from history object
                Question question = questionRepository.findById(history.getQuestion().getQuestionId()).get();

                //get correct answer from answer repo based on question  id
                Answer correct = answerRepository.findByQuestion_QuestionIdAndIsCorrect(question.getQuestionId(), true);

                //create AnswerSummaryDetails object
                AnswerSummaryDetails answerSummaryDetails = new AnswerSummaryDetails();

                //set question into answerSummaryDetails
                answerSummaryDetails.setQuestion(question.getQuestionText());

                //set correct answer into answerSummaryDetails
                answerSummaryDetails.setCorrect(correct.getOption()+") "+correct.getAnswerText());

                //if history option is null then
                if (history.getOption()==null) {
                    //set selected to null of answerSummary
                    answerSummaryDetails.setSelected("none");
                } else {

                    //get userAnswer based on question id and option
                    Answer userAnswer = answerRepository.findByQuestion_QuestionIdAndOption(question.getQuestionId(), history.getOption());

                    //set selected answer on answerSummary
                    answerSummaryDetails.setSelected(userAnswer.getOption()+") "+userAnswer.getAnswerText());
                }

                //add this answerSummary on summaryDetails list
                summaryDetails.add(answerSummaryDetails);
            }

            //at last return response entity object
            return new ResponseEntity<>(createResponseOfResult(session, summaryDetails), HttpStatus.OK);
        } catch (NoSuchElementException e) {

            // this will return where no data present
            return new ResponseEntity<>("No Element Present", HttpStatus.NOT_FOUND);
        } catch (Exception e) {

            //this will return when some exception occur internally
            return new ResponseEntity<>("Internal Server Exception", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
