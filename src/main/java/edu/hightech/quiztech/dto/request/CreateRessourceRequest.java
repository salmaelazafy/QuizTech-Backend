package edu.hightech.quiztech.dto.request;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CreateRessourceRequest {
    private String titre;
    private String description;
    private String fichier;
    private LocalDateTime datePublication;
    private Long classeId;
    private Long enseignantId;
}