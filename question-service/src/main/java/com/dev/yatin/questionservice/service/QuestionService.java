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

    /**
     * Retrieves all questions from the database.
     *
     * @return a ResponseEntity containing the list of all Question objects with HTTP status 200 (OK), or an empty list with HTTP status 400 (Bad Request) if an error occurs
     */
    public ResponseEntity<List<Question>> getAllQuestions() {
       try {
           return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
       } catch (Exception e) {
           e.printStackTrace();
       }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Retrieves all questions associated with the specified category ID.
     *
     * @param category the ID of the category to filter questions by
     * @return a list of questions belonging to the given category, or an empty list if the category does not exist or the ID is invalid
     */
    public List<Question> getQuestionsByCategory(int category) {
        if(category <= 0) new ResponseEntity<>("Negative values not acceptable", HttpStatus.BAD_REQUEST);
        Optional<Category> cat = categoryRepository.findById(category);
        if(cat.isEmpty()) return new ArrayList<>();
        return questionRepository.findByCategory(cat.get());
    }

    /**
     * Saves a new Question entity to the database and returns it with HTTP status 201 (Created).
     *
     * @param question the Question entity to be added
     * @return a ResponseEntity containing the saved Question and HTTP status 201 (Created)
     */
    public ResponseEntity<Question> addQuestion(Question question) {
        return new ResponseEntity<>(questionRepository.save(question), HttpStatus.CREATED);
    }
} 