package com.dev.yatin.quizapp.service;

import com.dev.yatin.quizapp.dao.QuestionDao;
import com.dev.yatin.quizapp.dao.QuizDao;
import com.dev.yatin.quizapp.entity.Question;
import com.dev.yatin.quizapp.entity.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category,int noOfQues,String title) {
        if(title.isEmpty()) return new ResponseEntity<>("Must add title", HttpStatus.BAD_REQUEST);
        if(category.isEmpty()) return new ResponseEntity<>("Must add category", HttpStatus.BAD_REQUEST);
        if(noOfQues <= 0) return new ResponseEntity<>("noOfQues must be greater then 0", HttpStatus.BAD_REQUEST);
        if(!isCategoryExist(category)) return new ResponseEntity<>("Not a valid category", HttpStatus.BAD_REQUEST);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        List<Question> questions = questionDao.findRandomQuestionsById(category,noOfQues);
        quiz.setQuestions(questions);
        quizDao.save(quiz);
        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }

    public boolean isCategoryExist(String category) {
        //Can optimize this by making category as many to one map in diff table for better querying
        /* Advantages of optimization
        No need for DISTINCT queries anymore.
        Faster EXISTS check via indexed foreign key.
        Easy to update or rename categories in one place.
        Enforces referential integrity using foreign key constraints.
        Cleaner and scalable design (especially when categories grow or evolve).
        * */

        List<String> categories = questionDao.findDistinctCategories();
        return categories.contains(category);
    }
}
