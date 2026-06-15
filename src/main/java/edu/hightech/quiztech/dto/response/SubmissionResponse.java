package edu.hightech.quiztech.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SubmissionResponse {
    private Long id;
    private LocalDateTime dateDebut;
    private LocalDateTime dateSoumission;
    private Double note;
    private String commentaire;
    private Boolean isGraded;
    private Integer antiFraudIncidentCount;
    private String antiFraudDetails;
    private String etudiantNom;
    private String examenTitre;
    private int nombreReponses;
}