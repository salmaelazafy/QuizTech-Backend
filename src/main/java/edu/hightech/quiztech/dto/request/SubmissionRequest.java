package edu.hightech.quiztech.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class SubmissionRequest {

    @NotNull(message = "L'étudiant est obligatoire")
    private Long etudiantId;

    @NotNull(message = "L'examen est obligatoire")
    private Long examenId;

    private List<ReponseEtudiantRequest> reponses;
}