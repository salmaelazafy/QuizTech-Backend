package edu.hightech.quiztech.dto.response;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AuditLogResponse {
    private Long id;
    private String action; // CREATE_EXAM, UPDATE_SCORE...
    private String details;
    private LocalDateTime dateAction;
    private Long utilisateurId;
    private String utilisateurNom;
}