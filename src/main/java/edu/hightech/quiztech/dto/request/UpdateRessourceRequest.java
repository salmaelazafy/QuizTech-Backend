package edu.hightech.quiztech.dto.request;

import lombok.Data;

@Data
public class UpdateRessourceRequest {
    private String titre;
    private String description;
    private String fichier;
    private Long classeId;
}