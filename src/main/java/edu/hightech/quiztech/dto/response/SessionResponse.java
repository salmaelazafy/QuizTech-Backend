package edu.hightech.quiztech.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class SessionResponse {
    private Long submissionId;
    private Long etudiantId;
    private String etudiantNom;
    private Long examenId;
    private String examenTitre;
    private LocalDateTime dateDebut;
    private Integer tempsRestantMinutes;
    private Boolean isActive;
}