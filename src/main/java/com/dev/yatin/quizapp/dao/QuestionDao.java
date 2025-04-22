package com.dev.yatin.quizapp.dao;

import com.dev.yatin.quizapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionDao extends JpaRepository<Question,Long> {

    List<Question> findByCategory(String category);

    @Query(value = "SELECT * FROM questions q WHERE q.category = :category ORDER BY RANDOM() LIMIT :noOfQues",nativeQuery = true)
    List<Question> findRandomQuestionsById(String category, int noOfQues);

    @Query(value = "SELECT DISTINCT q.category FROM questions q",nativeQuery = true)
    List<String> findDistinctCategories();
}
