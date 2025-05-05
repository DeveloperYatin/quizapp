package com.dev.yatin.quizservice.service;

import com.dev.yatin.quizservice.client.QuestionClient;
import com.dev.yatin.quizservice.dto.*;
import com.dev.yatin.quizservice.entity.Quiz;
import com.dev.yatin.quizservice.repository.QuizRepository;
import com.dev.yatin.quizservice.utils.OptionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private QuestionClient questionClient;

    /**
     * Creates a new quiz with the specified title, category, and number of questions.
     *
     * Validates input parameters and retrieves a random set of questions from the specified category.
     * If successful, saves the quiz and returns a confirmation message.
     * Returns an error message with HTTP 400 status if validation fails or the category is not found.
     *
     * @param categoryName the name of the category from which to select questions
     * @param noOfQues the number of questions to include in the quiz
     * @param title the title of the quiz
     * @return a ResponseEntity containing a success or error message and the appropriate HTTP status code
     */
    public ResponseEntity<String> createQuiz(String categoryName, int noOfQues, String title) {
        if(title.isEmpty()) return new ResponseEntity<>("Must add title", HttpStatus.BAD_REQUEST);
        if(categoryName.isEmpty()) return new ResponseEntity<>("Must add category", HttpStatus.BAD_REQUEST);
        if(noOfQues <= 0) return new ResponseEntity<>("noOfQues must be greater then 0", HttpStatus.BAD_REQUEST);
        
        Optional<CategoryDto> categoryOpt = questionClient.findCategoryByName(categoryName);
        if(categoryOpt.isEmpty()) {
            return new ResponseEntity<>("Not a valid category", HttpStatus.BAD_REQUEST);
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        List<QuestionDto> questions = questionClient.getRandomQuestions(categoryOpt.get().getId(), noOfQues);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }

    /**
     * Retrieves all available quiz categories.
     *
     * @return a ResponseEntity containing a list of category DTOs with HTTP 200 status
     */
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(questionClient.getAllCategories(), HttpStatus.OK);
    }

    /**
     * Retrieves the list of questions for a given quiz ID.
     *
     * @param quizId the ID of the quiz to fetch questions for
     * @return a ResponseEntity containing the list of QuestionDto objects with HTTP 200 if the quiz exists,
     *         or an empty list with HTTP 400 if the quiz is not found
     */
    public ResponseEntity<List<QuestionDto>> getQuestions(Long quizId) {
        Optional<Quiz> quizData = quizRepository.findById(quizId);
        return quizData.map(quiz -> new ResponseEntity<>(quiz.getQuestions(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST));
    }

    /**
     * Evaluates a user's quiz submission and returns the result for each answered question.
     *
     * For each answer in the user's response, checks if the selected option is valid and matches the correct answer for the corresponding question in the quiz. Returns a list of result strings ("Correct", "Wrong", "Not a valid option", or "Not a valid question id") for each answer.
     *
     * @param quizId the ID of the quiz being submitted
     * @param userResponse the user's submitted answers
     * @return a ResponseEntity containing a list of result strings for each answer, or an empty list with HTTP 400 if the quiz is not found or the quizId is invalid
     */
    public ResponseEntity<List<String>> submitQuiz(Long quizId, Response userResponse) {
        Optional<Quiz> quizData = quizRepository.findById(quizId);
        ArrayList<String> answers = new ArrayList<>();

        if(quizId == 0) return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

        if(quizData.isPresent()){
            List<QuestionDto> questions = quizData.get().getQuestions();
            for(AnswerRequest responseQues : userResponse.getData()) {

                OptionType optionUserSelected = OptionType.fromString(responseQues.getOption_selected());
                if(optionUserSelected == null) { answers.add("Not a valid option"); continue; }

                QuestionDto ques = questions.stream()
                        .filter(item -> item.getId().equals(responseQues.getQuestion_id()))
                        .findFirst()
                        .orElse(null);

                if(ques == null) { answers.add("Not a valid question id"); continue; }

                if(ques.getCorrectOption().equals(optionUserSelected.getValue()) &&
                        responseQues.getQuestion_id().equals(ques.getId())) answers.add("Correct");
                else answers.add("Wrong");
            }
            return new ResponseEntity<>(answers, HttpStatus.OK);
        }
        else return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
} 