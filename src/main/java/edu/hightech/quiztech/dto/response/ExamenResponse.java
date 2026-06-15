package edu.hightech.quiztech.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExamenResponse {
    private Long id;
    private String titre;
    private String description;
    private Integer duree;
    private Integer coefficient;
    private LocalDateTime dateOuverture;
    private LocalDateTime dateFermeture;
    private String enseignantNom;
    private int nombreQuestions;
    private int nombreSubmissions;
}