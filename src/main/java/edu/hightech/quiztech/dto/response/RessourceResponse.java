package edu.hightech.quiztech.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class RessourceResponse {
    private Long id;
    private String titre;
    private String description;
    private String fichier;
    private LocalDateTime datePublication;
    private Long classeId;
    private String nomClasse;
    private String enseignantNom;
}