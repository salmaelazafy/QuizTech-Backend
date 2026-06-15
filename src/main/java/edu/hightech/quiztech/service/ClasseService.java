package edu.hightech.quiztech.service;

import edu.hightech.quiztech.dto.request.CreateClasseRequest;
import edu.hightech.quiztech.dto.response.ClasseResponse;
import java.util.List;

public interface ClasseService {
    ClasseResponse createClasse(CreateClasseRequest request);
    ClasseResponse updateClasse(Long id, CreateClasseRequest request);
    List<ClasseResponse> getAllClasses();
    void deleteClasse(Long id);
}