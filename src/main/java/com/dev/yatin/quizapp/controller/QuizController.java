package com.dev.yatin.quizapp.controller;

import com.dev.yatin.quizapp.entity.Category;
import com.dev.yatin.quizapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;


    @PostMapping("createQuiz")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int noOfQues,@RequestParam String title) {
        return quizService.createQuiz(category,noOfQues,title);
    }

    @GetMapping("getAllCategories")
    public ResponseEntity<List<Category>> getAllCategories() {
        return quizService.getAllCategories();
    }
 }
