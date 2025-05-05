package com.dev.yatin.questionservice.service;

import com.dev.yatin.questionservice.repository.CategoryRepository;
import com.dev.yatin.questionservice.repository.QuestionRepository;
import com.dev.yatin.questionservice.entity.Category;
import com.dev.yatin.questionservice.entity.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public ResponseEntity<List<Question>> getAllQuestions() {
       try {
           return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
       } catch (Exception e) {
           e.printStackTrace();
       }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public List<Question> getQuestionsByCategory(int category) {
        if(category <= 0) new ResponseEntity<>("Negative values not acceptable", HttpStatus.BAD_REQUEST);
        Optional<Category> cat = categoryRepository.findById(category);
        if(cat.isEmpty()) return new ArrayList<>();
        return questionRepository.findByCategory(cat.get());
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        return new ResponseEntity<>(questionRepository.save(question), HttpStatus.CREATED);
    }
} 