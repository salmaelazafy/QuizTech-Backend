package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.dto.request.NotificationRequest;
import edu.hightech.quiztech.dto.response.NotificationResponse;
import edu.hightech.quiztech.entity.Notification;
import edu.hightech.quiztech.entity.Utilisateur;
import edu.hightech.quiztech.repository.NotificationRepository;
import edu.hightech.quiztech.repository.UtilisateurRepository;
import edu.hightech.quiztech.service.NotificationService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    public NotificationResponse creerNotification(NotificationRequest request) {
        Utilisateur utilisateur = utilisateurRepository.findById(request.getUtilisateurId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Utilisateur non trouvé avec l'id : " + request.getUtilisateurId()));

        Notification notification = new Notification();
        notification.setTitre(request.getTitre());
        notification.setMessage(request.getMessage());
        notification.setType(request.getType());
        notification.setUtilisateur(utilisateur);
        notification.setDateNotification(LocalDateTime.now());
        notification.setEstLue(false);

        return toResponse(notificationRepository.save(notification));
    }

    @Override
    public List<NotificationResponse> getNotificationsParUtilisateur(Long utilisateurId) {
        verifierUtilisateur(utilisateurId);
        return notificationRepository
                .findByUtilisateurIdOrderByDateNotificationDesc(utilisateurId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponse> getNotificationsNonLues(Long utilisateurId) {
        verifierUtilisateur(utilisateurId);
        return notificationRepository
                .findByUtilisateurIdAndEstLueFalseOrderByDateNotificationDesc(utilisateurId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public long compterNotificationsNonLues(Long utilisateurId) {
        verifierUtilisateur(utilisateurId);
        return notificationRepository.countByUtilisateurIdAndEstLueFalse(utilisateurId);
    }

    @Override
    public NotificationResponse marquerCommeLue(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Notification non trouvée avec l'id : " + notificationId));

        notification.setEstLue(true);
        return toResponse(notificationRepository.save(notification));
    }

    @Override
    @Transactional
    public void marquerToutesCommeLues(Long utilisateurId) {
        verifierUtilisateur(utilisateurId);
        notificationRepository.marquerToutesCommeLues(utilisateurId);
    }

    @Override
    public void supprimerNotification(Long notificationId) {
        if (!notificationRepository.existsById(notificationId)) {
            throw new EntityNotFoundException("Notification non trouvée avec l'id : " + notificationId);
        }
        notificationRepository.deleteById(notificationId);
    }

    @Override
    public void envoyerNotification(Long utilisateurId, String titre, String message, String type) {
        Utilisateur utilisateur = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Utilisateur non trouvé avec l'id : " + utilisateurId));

        Notification notification = new Notification();
        notification.setTitre(titre);
        notification.setMessage(message);
        notification.setType(type);
        notification.setUtilisateur(utilisateur);
        notification.setDateNotification(LocalDateTime.now());
        notification.setEstLue(false);

        notificationRepository.save(notification);
    }

    // ===== Helper =====

    private void verifierUtilisateur(Long utilisateurId) {
        if (!utilisateurRepository.existsById(utilisateurId)) {
            throw new EntityNotFoundException("Utilisateur non trouvé avec l'id : " + utilisateurId);
        }
    }

    private NotificationResponse toResponse(Notification notification) {
        NotificationResponse response = new NotificationResponse();
        response.setId(notification.getId());
        response.setTitre(notification.getTitre());
        response.setMessage(notification.getMessage());
        response.setDateNotification(notification.getDateNotification());
        response.setEstLue(notification.getEstLue());
        response.setType(notification.getType());
        response.setUtilisateurId(notification.getUtilisateur().getId());
        response.setUtilisateurNom(notification.getUtilisateur().getNomComplet());
        return response;
    }
}