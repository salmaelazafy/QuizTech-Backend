package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.dto.response.SubmissionResponse;
import edu.hightech.quiztech.entity.Submission;
import edu.hightech.quiztech.repository.SubmissionRepository;
import edu.hightech.quiztech.service.SoumissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl {

    private final SubmissionRepository submissionRepository;

    @Transactional(readOnly = true)
    public SubmissionResponse getSubmissionById(Long id) {
        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Submission introuvable"));
        return mapToResponse(submission);
    }

    @Transactional(readOnly = true)
    public List<SubmissionResponse> getSubmissionsByEtudiant(Long etudiantId) {
        return submissionRepository.findAll().stream()
                .filter(s -> s.getEtudiant() != null && s.getEtudiant().getId().equals(etudiantId))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private SubmissionResponse mapToResponse(Submission submission) {
        SubmissionResponse res = new SubmissionResponse();
        res.setId(submission.getId());
        res.setDateDebut(submission.getDateDebut());
        res.setDateSoumission(submission.getDateSoumission());
        res.setCommentaire(submission.getCommentaire());
        res.setIsGraded(submission.getIsGraded());
        res.setAntiFraudIncidentCount(submission.getAntiFraudIncidentCount());
        res.setAntiFraudDetails(submission.getAntiFraudDetails());
        res.setNote(submission.getNote());
        if (submission.getEtudiant() != null) {
            res.setEtudiantNom(submission.getEtudiant().getNomComplet());
        }

        if (submission.getExamen() != null) {
            res.setExamenTitre(submission.getExamen().getTitre()); }

        res.setNombreReponses(submission.getReponses() != null ? submission.getReponses().size() : 0);

        return res;
    }
}