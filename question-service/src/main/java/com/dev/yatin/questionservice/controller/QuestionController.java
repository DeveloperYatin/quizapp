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

    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public List<Question> getQuestionsByCategory(@PathVariable int category) {
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }
} 