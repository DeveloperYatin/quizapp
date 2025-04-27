package com.dev.yatin.quizapp.service;

import com.dev.yatin.quizapp.dao.QuestionDao;
import com.dev.yatin.quizapp.dao.QuizDao;
import com.dev.yatin.quizapp.dao.CategoryDao;
import com.dev.yatin.quizapp.entity.Question;
import com.dev.yatin.quizapp.entity.Quiz;
import com.dev.yatin.quizapp.entity.Category;
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
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    @Autowired
    CategoryDao categoryDao;

    public ResponseEntity<String> createQuiz(String categoryName, int noOfQues, String title) {
        if(title.isEmpty()) return new ResponseEntity<>("Must add title", HttpStatus.BAD_REQUEST);
        if(categoryName.isEmpty()) return new ResponseEntity<>("Must add category", HttpStatus.BAD_REQUEST);
        if(noOfQues <= 0) return new ResponseEntity<>("noOfQues must be greater then 0", HttpStatus.BAD_REQUEST);
        
        Optional<Category> categoryOpt = categoryDao.findByName(categoryName);
        if(categoryOpt.isEmpty()) {
            return new ResponseEntity<>("Not a valid category", HttpStatus.BAD_REQUEST);
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        List<Question> questions = questionDao.findRandomQuestionsById(categoryOpt.get().getId(), noOfQues);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<Category>> getAllCategories() {
        return new ResponseEntity<>(categoryDao.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<List<Question>> getQuestions(Long quizId) {
        Optional<Quiz> quizData = quizDao.findById(quizId);
        return quizData.map(quiz -> new ResponseEntity<>(quiz.getQuestions(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST));
    }
}
