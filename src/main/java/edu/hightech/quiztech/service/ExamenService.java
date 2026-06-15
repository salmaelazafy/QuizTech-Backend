package edu.hightech.quiztech.service;

import edu.hightech.quiztech.dto.request.CreateExamenRequest;
import edu.hightech.quiztech.dto.request.UpdateExamenRequest;
import edu.hightech.quiztech.dto.response.ExamenResponse;
import java.util.List;

public interface ExamenService {
    ExamenResponse createExamen(CreateExamenRequest request);
    ExamenResponse updateExamen(Long id, UpdateExamenRequest request);
    ExamenResponse getExamenById(Long id);
    List<ExamenResponse> getAllExamens();
    List<ExamenResponse> getExamensByClasse(Long classeId);
    void deleteExamen(Long id);
}