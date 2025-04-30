package com.dev.yatin.quizapp.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AnswerRequest {
    private String option_selected;
    private Long question_id;
}