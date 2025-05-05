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

    @GetMapping("/category/{categoryId}/random")
    List<QuestionDto> getRandomQuestions(@PathVariable Integer categoryId, @RequestParam int count);

    @GetMapping("/categories")
    List<CategoryDto> getAllCategories();

    @GetMapping("/categories/name/{name}")
    Optional<CategoryDto> findCategoryByName(@PathVariable String name);
} 