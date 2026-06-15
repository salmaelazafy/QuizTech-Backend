package edu.hightech.quiztech.dto.request;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String nomComplet;
    private String photoProfil;
    private Boolean forcePasswordChange;
    private Long classeId;
}