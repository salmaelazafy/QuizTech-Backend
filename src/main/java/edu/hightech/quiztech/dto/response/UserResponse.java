package edu.hightech.quiztech.dto.response;

import edu.hightech.quiztech.entity.enums.Role;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserResponse {
    private Long id;
    private String nomComplet;
    private String email;
    private String photoProfil;
    private LocalDate dateInscription;
    private LocalDateTime lastActivity;
    private Boolean forcePasswordChange;
    private Role role;


    private Long classeId;
    private String classeNom;
}