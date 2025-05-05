package com.dev.yatin.quizservice.service;

import com.dev.yatin.quizservice.client.QuestionClient;
import com.dev.yatin.quizservice.dto.*;
import com.dev.yatin.quizservice.entity.Quiz;
import com.dev.yatin.quizservice.repository.QuizRepository;
import com.dev.yatin.quizservice.utils.OptionType;
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
    private QuizRepository quizRepository;

    @Autowired
    private QuestionClient questionClient;

    public ResponseEntity<String> createQuiz(String categoryName, int noOfQues, String title) {
        if(title.isEmpty()) return new ResponseEntity<>("Must add title", HttpStatus.BAD_REQUEST);
        if(categoryName.isEmpty()) return new ResponseEntity<>("Must add category", HttpStatus.BAD_REQUEST);
        if(noOfQues <= 0) return new ResponseEntity<>("noOfQues must be greater then 0", HttpStatus.BAD_REQUEST);
        
        Optional<CategoryDto> categoryOpt = questionClient.findCategoryByName(categoryName);
        if(categoryOpt.isEmpty()) {
            return new ResponseEntity<>("Not a valid category", HttpStatus.BAD_REQUEST);
        }

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        List<QuestionDto> questions = questionClient.getRandomQuestions(categoryOpt.get().getId(), noOfQues);
        quiz.setQuestions(questions);
        quizRepository.save(quiz);
        return new ResponseEntity<>("Quiz created successfully", HttpStatus.CREATED);
    }

    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return new ResponseEntity<>(questionClient.getAllCategories(), HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDto>> getQuestions(Long quizId) {
        Optional<Quiz> quizData = quizRepository.findById(quizId);
        return quizData.map(quiz -> new ResponseEntity<>(quiz.getQuestions(), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST));
    }

    public ResponseEntity<List<String>> submitQuiz(Long quizId, Response userResponse) {
        Optional<Quiz> quizData = quizRepository.findById(quizId);
        ArrayList<String> answers = new ArrayList<>();

        if(quizId == 0) return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

        if(quizData.isPresent()){
            List<QuestionDto> questions = quizData.get().getQuestions();
            for(AnswerRequest responseQues : userResponse.getData()) {

                OptionType optionUserSelected = OptionType.fromString(responseQues.getOption_selected());
                if(optionUserSelected == null) { answers.add("Not a valid option"); continue; }

                QuestionDto ques = questions.stream()
                        .filter(item -> item.getId().equals(responseQues.getQuestion_id()))
                        .findFirst()
                        .orElse(null);

                if(ques == null) { answers.add("Not a valid question id"); continue; }

                if(ques.getCorrectOption().equals(optionUserSelected.getValue()) &&
                        responseQues.getQuestion_id().equals(ques.getId())) answers.add("Correct");
                else answers.add("Wrong");
            }
            return new ResponseEntity<>(answers, HttpStatus.OK);
        }
        else return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }
} 