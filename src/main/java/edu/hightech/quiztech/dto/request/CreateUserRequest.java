package edu.hightech.quiztech.dto.request;

import edu.hightech.quiztech.entity.enums.Role;
import lombok.Data;

@Data
public class CreateUserRequest {
    private String nomComplet;
    private String email;
    private String motDePasse;
    private Role role; // ADMIN, ENSEIGNANT, ETUDIANT
    private String photoProfil;


    private Long classeId;
}