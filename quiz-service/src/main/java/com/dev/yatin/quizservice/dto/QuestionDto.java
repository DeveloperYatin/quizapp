package com.dev.yatin.quizservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {
    private Long id;
    private CategoryDto category;
    private String question;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctOption;
    private String difficulty;
} 