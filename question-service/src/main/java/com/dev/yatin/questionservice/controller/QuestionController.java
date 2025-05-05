package com.dev.yatin.questionservice.controller;

import com.dev.yatin.questionservice.entity.Question;
import com.dev.yatin.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    /****
     * Retrieves all questions.
     *
     * @return a ResponseEntity containing a list of all Question objects
     */
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    /**
     * Retrieves a list of questions filtered by the specified category ID.
     *
     * @param category the ID of the category to filter questions by
     * @return a list of questions belonging to the given category
     */
    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable int category) {
        return questionService.getQuestionsByCategory(category);
    }

    /**
     * Adds a new question to the system.
     *
     * @param question the question to be added, provided in the request body
     * @return a ResponseEntity containing the added Question
     */
    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
} 