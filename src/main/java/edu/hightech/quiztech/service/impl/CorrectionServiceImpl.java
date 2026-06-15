package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.dto.response.SubmissionResponse;
import edu.hightech.quiztech.entity.Submission;
import edu.hightech.quiztech.entity.ReponseEtudiant;
import edu.hightech.quiztech.repository.SubmissionRepository;
import edu.hightech.quiztech.service.CorrectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CorrectionServiceImpl implements CorrectionService {

    private final SubmissionRepository submissionRepository;

    @Override
    @Transactional
    public SubmissionResponse corrigerAutomatiquement(Long submissionId) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission introuvable"));

        double noteTotale = 0.0;

        if (submission.getReponses() != null) {
            for (ReponseEtudiant reponse : submission.getReponses()) {
                if (reponse.getChoix() != null && reponse.getChoix().getEstCorrect()) {
                    double noteQuestion = reponse.getQuestion() != null ? reponse.getQuestion().getNote() : 1.0;
                    reponse.setNoteObtenue(noteQuestion);
                    noteTotale += noteQuestion;
                } else {
                    reponse.setNoteObtenue(0.0);
                }
            }
        }

        submission.setNote(noteTotale);
        submission.setIsGraded(true);
        Submission saved = submissionRepository.save(submission);

        return mapToResponse(saved);
    }

    @Override
    @Transactional
    public SubmissionResponse ajusterNoteManuellement(Long submissionId, Double nouvelleNote, String commentaire) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission introuvable"));

        submission.setNote(nouvelleNote);
        submission.setCommentaire(commentaire);
        submission.setIsGraded(true);

        Submission saved = submissionRepository.save(submission);
        return mapToResponse(saved);
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
            res.setExamenTitre(submission.getExamen().getTitre());
        }
        res.setNombreReponses(submission.getReponses() != null ? submission.getReponses().size() : 0);

        return res;
    }
}