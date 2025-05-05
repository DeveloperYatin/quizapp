package com.dev.yatin.quizservice.repository;

import com.dev.yatin.quizservice.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {
} 