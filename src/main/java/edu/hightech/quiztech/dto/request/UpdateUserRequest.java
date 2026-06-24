package edu.hightech.quiztech.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UpdateUserRequest {

    @Size(min = 3, message = "Le nom doit contenir au moins 3 caractères")
    private String nomComplet;

    private String photoProfil;

    private Boolean forcePasswordChange;

    private Long classeId;
}