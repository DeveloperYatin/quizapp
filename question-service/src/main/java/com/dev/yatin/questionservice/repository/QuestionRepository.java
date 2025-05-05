package com.dev.yatin.questionservice.repository;

import com.dev.yatin.questionservice.entity.Question;
import com.dev.yatin.questionservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    List<Question> findByCategory(Category category);

    @Query(value = "SELECT * FROM questions q WHERE q.category_id = :categoryId ORDER BY RANDOM() LIMIT :noOfQues", nativeQuery = true)
    List<Question> findRandomQuestionsById(Integer categoryId, int noOfQues);

    @Query(value = "SELECT DISTINCT c.name FROM categories c", nativeQuery = true)
    List<String> findDistinctCategories();
} 