package edu.hightech.quiztech.service;

import edu.hightech.quiztech.dto.request.CreateQuestionRequest;
import edu.hightech.quiztech.dto.response.QuestionResponse;
import java.util.List;

public interface QuestionService {
    QuestionResponse createQuestion(CreateQuestionRequest request);
    QuestionResponse getQuestionById(Long id);
    List<QuestionResponse> getQuestionsByExamen(Long examenId);
    void deleteQuestion(Long id);
}