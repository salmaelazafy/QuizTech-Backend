package edu.hightech.quiztech.service;

import edu.hightech.quiztech.dto.request.SubmissionRequest;
import edu.hightech.quiztech.dto.response.SubmissionResponse;
import java.util.List;

public interface SoumissionService {
    SubmissionResponse soumettreExamen(SubmissionRequest request);
    SubmissionResponse getSoumissionById(Long id);
    List<SubmissionResponse> getSoumissionsByEtudiant(Long etudiantId);
}