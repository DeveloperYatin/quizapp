package com.dev.yatin.quizapp.service;

import com.dev.yatin.quizapp.dao.CategoryDao;
import com.dev.yatin.quizapp.dao.QuestionDao;
import com.dev.yatin.quizapp.entity.Category;
import com.dev.yatin.quizapp.entity.Question;
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
    QuestionDao questionDao;

    @Autowired
    CategoryDao categoryDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
       try {
           return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
       } catch (Exception e) {
           e.printStackTrace();
       }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public List<Question> getQuestionsByCategory(int category) {
        if(category <= 0) new ResponseEntity<>("Negative values not acceptable", HttpStatus.BAD_REQUEST);
        Optional<Category> cat = categoryDao.findById(category);
        if(cat.isEmpty()) return new ArrayList<>();
        return questionDao.findByCategory(cat.get());
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        return new ResponseEntity<>(questionDao.save(question),HttpStatus.CREATED);
    }
}
