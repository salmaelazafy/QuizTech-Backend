package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.dto.request.SubmissionRequest;
import edu.hightech.quiztech.dto.response.SubmissionResponse;
import edu.hightech.quiztech.entity.Etudiant;
import edu.hightech.quiztech.entity.Submission;
import edu.hightech.quiztech.repository.SubmissionRepository;
import edu.hightech.quiztech.service.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;

    @Override
    @Transactional
    public SubmissionResponse soumettreExamen(SubmissionRequest request) {

        Submission submission = new Submission();

        // TODO : compléter l'implémentation plus tard
        // submission.setEtudiant(...);
        // submission.setExamen(...);
        // submission.setDateDebut(...);
        // submission.setDateSoumission(...);

        Submission savedSubmission = submissionRepository.save(submission);

        return mapToResponse(savedSubmission);
    }

    @Override
    @Transactional(readOnly = true)
    public SubmissionResponse getSoumissionById(Long id) {

        Submission submission = submissionRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Submission introuvable avec l'id : " + id));

        return mapToResponse(submission);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubmissionResponse> getSoumissionsByEtudiant(Long etudiantId) {

        return submissionRepository.findByEtudiantId(etudiantId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    private SubmissionResponse mapToResponse(Submission submission) {

        SubmissionResponse response = new SubmissionResponse();

        response.setId(submission.getId());
        response.setDateDebut(submission.getDateDebut());
        response.setDateSoumission(submission.getDateSoumission());
        response.setCommentaire(submission.getCommentaire());
        response.setNote(submission.getNote());

        response.setIsGraded(
                submission.getIsGraded() != null
                        ? submission.getIsGraded()
                        : false
        );

        if (submission.getEtudiant() != null) {
            response.setEtudiantNom(
                    submission.getEtudiant().getNomComplet()
            );
        }

        if (submission.getExamen() != null) {
            response.setExamenTitre(
                    submission.getExamen().getTitre()
            );
        }

        response.setNombreReponses(
                submission.getReponses() != null
                        ? submission.getReponses().size()
                        : 0
        );

        return response;
    }
}