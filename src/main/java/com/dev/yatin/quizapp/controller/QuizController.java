package com.dev.yatin.quizapp.controller;

import com.dev.yatin.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService questionService;

    @PostMapping("createQuiz")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int noOfQues,@RequestParam String title) {
        return questionService.createQuiz(category,noOfQues,title);
    }
 }
