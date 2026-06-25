package edu.hightech.quiztech.service;

import edu.hightech.quiztech.dto.request.NotificationRequest;
import edu.hightech.quiztech.dto.response.NotificationResponse;

import java.util.List;

public interface NotificationService {


    NotificationResponse creerNotification(NotificationRequest request);

    
    List<NotificationResponse> getNotificationsParUtilisateur(Long utilisateurId);

    
    List<NotificationResponse> getNotificationsNonLues(Long utilisateurId);

   
    long compterNotificationsNonLues(Long utilisateurId);

    
    NotificationResponse marquerCommeLue(Long notificationId);

    
    void marquerToutesCommeLues(Long utilisateurId);

    
    void supprimerNotification(Long notificationId);

   
    void envoyerNotification(Long utilisateurId, String titre, String message, String type);
}