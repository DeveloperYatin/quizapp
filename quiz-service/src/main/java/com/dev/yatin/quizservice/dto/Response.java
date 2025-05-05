package com.dev.yatin.quizservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class Response {
    private List<AnswerRequest> data;
    private Long quizId;
} 