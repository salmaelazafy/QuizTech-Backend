package edu.hightech.quiztech.dto.response;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private String token;
    private String email;
    private String nomComplet;
    private String role; // ENSEIGNANT, ETUDIANT, ADMIN
}