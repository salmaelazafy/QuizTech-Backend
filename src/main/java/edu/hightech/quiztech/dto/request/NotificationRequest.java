package edu.hightech.quiztech.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class NotificationRequest {

    @NotBlank(message = "Le titre est obligatoire")
    private String titre;

    @NotBlank(message = "Le message est obligatoire")
    private String message;


    @NotBlank(message = "Le type est obligatoire")
    private String type;

    
    @NotNull(message = "L'ID utilisateur est obligatoire")
    private Long utilisateurId;
}