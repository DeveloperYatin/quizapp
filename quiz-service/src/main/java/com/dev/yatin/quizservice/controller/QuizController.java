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

    @PostMapping
    public ResponseEntity<String> createQuiz(
            @RequestParam String category,
            @RequestParam int noOfQues,
            @RequestParam String title) {
        return quizService.createQuiz(category, noOfQues, title);
    }

    @GetMapping("categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return quizService.getAllCategories();
    }

    @GetMapping("{quizId}")
    public ResponseEntity<List<QuestionDto>> getQuestions(@PathVariable Long quizId) {
        return quizService.getQuestions(quizId);
    }

    @PostMapping("{quizId}/submit")
    public ResponseEntity<List<String>> submitQuiz(
            @PathVariable Long quizId,
            @RequestBody Response userResponse) {
        return quizService.submitQuiz(quizId, userResponse);
    }
} 