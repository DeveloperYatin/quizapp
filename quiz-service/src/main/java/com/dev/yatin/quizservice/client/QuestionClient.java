package com.dev.yatin.quizservice.client;

import com.dev.yatin.quizservice.dto.CategoryDto;
import com.dev.yatin.quizservice.dto.QuestionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "question-service", path = "/api/v1/questions")
public interface QuestionClient {

    /**
     * Retrieves a list of random questions from a specified category.
     *
     * @param categoryId the ID of the category to select questions from
     * @param count the number of random questions to retrieve
     * @return a list of random questions from the given category
     */
    @GetMapping("/category/{categoryId}/random")
    List<QuestionDto> getRandomQuestions(@PathVariable Integer categoryId, @RequestParam int count);

    /**
     * Retrieves a list of all available question categories.
     *
     * @return a list of CategoryDto objects representing all categories
     */
    @GetMapping("/categories")
    List<CategoryDto> getAllCategories();

    /**
     * Retrieves a category by its name.
     *
     * @param name the name of the category to search for
     * @return an Optional containing the matching CategoryDto if found, or empty if not found
     */
    @GetMapping("/categories/name/{name}")
    Optional<CategoryDto> findCategoryByName(@PathVariable String name);
} 