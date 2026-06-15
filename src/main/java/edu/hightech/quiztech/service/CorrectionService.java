package edu.hightech.quiztech.service;

import edu.hightech.quiztech.dto.response.SubmissionResponse;

public interface CorrectionService {
    SubmissionResponse corrigerAutomatiquement(Long submissionId);
    SubmissionResponse ajusterNoteManuellement(Long submissionId, Double nouvelleNote, String commentaire);
}