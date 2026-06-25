package edu.hightech.quiztech.controller;

import edu.hightech.quiztech.dto.request.NotificationRequest;
import edu.hightech.quiztech.dto.response.NotificationResponse;
import edu.hightech.quiztech.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService notificationService;

    // POST /api/notifications 
    @PostMapping
    public ResponseEntity<NotificationResponse> creerNotification(
            @Valid @RequestBody NotificationRequest request) {
        return ResponseEntity.ok(notificationService.creerNotification(request));
    }

    // GET /api/notifications/utilisateur/{id}
    @GetMapping("/utilisateur/{utilisateurId}")
    public ResponseEntity<List<NotificationResponse>> getNotificationsParUtilisateur(
            @PathVariable Long utilisateurId) {
        return ResponseEntity.ok(notificationService.getNotificationsParUtilisateur(utilisateurId));
    }

    // GET /api/notifications/utilisateur/{id}/non-lues ة
    @GetMapping("/utilisateur/{utilisateurId}/non-lues")
    public ResponseEntity<List<NotificationResponse>> getNotificationsNonLues(
            @PathVariable Long utilisateurId) {
        return ResponseEntity.ok(notificationService.getNotificationsNonLues(utilisateurId));
    }

    // GET /api/notifications/utilisateur/{id}/count 
    @GetMapping("/utilisateur/{utilisateurId}/count")
    public ResponseEntity<Map<String, Long>> compterNonLues(
            @PathVariable Long utilisateurId) {
        long count = notificationService.compterNotificationsNonLues(utilisateurId);
        return ResponseEntity.ok(Map.of("nonLues", count));
    }

    // PATCH /api/notifications/{id}/lire 
    @PatchMapping("/{notificationId}/lire")
    public ResponseEntity<NotificationResponse> marquerCommeLue(
            @PathVariable Long notificationId) {
        return ResponseEntity.ok(notificationService.marquerCommeLue(notificationId));
    }

    // PATCH /api/notifications/utilisateur/{id}/lire-tout 
    @PatchMapping("/utilisateur/{utilisateurId}/lire-tout")
    public ResponseEntity<Void> marquerToutesCommeLues(
            @PathVariable Long utilisateurId) {
        notificationService.marquerToutesCommeLues(utilisateurId);
        return ResponseEntity.noContent().build();
    }

    // DELETE /api/notifications/{id}
    @DeleteMapping("/{notificationId}")
    public ResponseEntity<Void> supprimerNotification(
            @PathVariable Long notificationId) {
        notificationService.supprimerNotification(notificationId);
        return ResponseEntity.noContent().build();
    }
}