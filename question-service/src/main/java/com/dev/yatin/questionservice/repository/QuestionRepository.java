package com.dev.yatin.questionservice.repository;

import com.dev.yatin.questionservice.entity.Question;
import com.dev.yatin.questionservice.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    /**
 * Retrieves all questions associated with the specified category.
 *
 * @param category the category to filter questions by
 * @return a list of questions belonging to the given category
 */
List<Question> findByCategory(Category category);

    @Query(value = "SELECT * FROM questions q WHERE q.category_id = :categoryId ORDER BY RANDOM() LIMIT :noOfQues", nativeQuery = true)
    List<Question> findRandomQuestionsById(Integer categoryId, int noOfQues);

    /**
     * Retrieves a list of unique category names from the categories table.
     *
     * @return a list of distinct category names
     */
    @Query(value = "SELECT DISTINCT c.name FROM categories c", nativeQuery = true)
    List<String> findDistinctCategories();
} 