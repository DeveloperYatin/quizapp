package com.dev.yatin.quizservice.controller;

import com.dev.yatin.quizservice.dto.CategoryDto;
import com.dev.yatin.quizservice.dto.QuestionDto;
import com.dev.yatin.quizservice.dto.Response;
import com.dev.yatin.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/quizzes")
public class QuizController {

    @Autowired
    QuizService quizService;

    /**
     * Handles HTTP POST requests to create a new quiz with the specified category, number of questions, and title.
     *
     * @param category the category for the new quiz
     * @param noOfQues the number of questions to include in the quiz
     * @param title the title of the quiz
     * @return a ResponseEntity containing the result message of the quiz creation
     */
    @PostMapping
    public ResponseEntity<String> createQuiz(
            @RequestParam String category,
            @RequestParam int noOfQues,
            @RequestParam String title) {
        return quizService.createQuiz(category, noOfQues, title);
    }

    /**
     * Retrieves all available quiz categories.
     *
     * @return a response entity containing a list of category DTOs representing all quiz categories
     */
    @GetMapping("categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return quizService.getAllCategories();
    }

    /**
     * Retrieves the list of questions for the specified quiz.
     *
     * @param quizId the unique identifier of the quiz
     * @return a response entity containing a list of question DTOs for the quiz
     */
    @GetMapping("{quizId}")
    public ResponseEntity<List<QuestionDto>> getQuestions(@PathVariable Long quizId) {
        return quizService.getQuestions(quizId);
    }

    /**
     * Submits user responses for a specific quiz and returns the result.
     *
     * @param quizId the ID of the quiz to submit answers for
     * @param userResponse the user's answers to the quiz questions
     * @return a response entity containing a list of result strings from the quiz submission
     */
    @PostMapping("{quizId}/submit")
    public ResponseEntity<List<String>> submitQuiz(
            @PathVariable Long quizId,
            @RequestBody Response userResponse) {
        return quizService.submitQuiz(quizId, userResponse);
    }
} 