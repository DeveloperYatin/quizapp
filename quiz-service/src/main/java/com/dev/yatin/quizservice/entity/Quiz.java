package com.dev.yatin.quizservice.entity;

import com.dev.yatin.quizservice.dto.QuestionDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "quiz")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @ElementCollection
    @CollectionTable(name = "quiz_questions", joinColumns = @JoinColumn(name = "quiz_id"))
    private List<QuestionDto> questions;
} 