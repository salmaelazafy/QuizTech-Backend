package edu.hightech.quiztech.controller;

import edu.hightech.quiztech.dto.response.AuditLogResponse;
import edu.hightech.quiztech.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuditController {

    private final AuditService auditService;

    @GetMapping
    public ResponseEntity<List<AuditLogResponse>> getAllLogs() {
        return ResponseEntity.ok(
                auditService.getAllLogs()
        );
    }

    @PostMapping("/log")
    public ResponseEntity<String> logAction(
            @RequestParam Long utilisateurId,
            @RequestParam String action,
            @RequestParam String details) {

        auditService.logAction(utilisateurId, action, details);

        return ResponseEntity.ok("Action enregistrée avec succès");
    }
}