package edu.hightech.quiztech.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotificationResponse {

    private Long id;
    private String titre;
    private String message;
    private LocalDateTime dateNotification;
    private Boolean estLue;
    private String type;

    
    private Long utilisateurId;
    private String utilisateurNom;
}