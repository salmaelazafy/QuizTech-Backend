package edu.hightech.quiztech.service;

import edu.hightech.quiztech.dto.response.AuditLogResponse;
import java.util.List;

public interface AuditService {
    void logAction(Long utilisateurId, String action, String details);
    List<AuditLogResponse> getAllLogs();
}