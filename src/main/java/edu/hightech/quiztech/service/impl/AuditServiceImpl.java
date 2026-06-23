package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.dto.response.AuditLogResponse;
import edu.hightech.quiztech.entity.AuditLog;
import edu.hightech.quiztech.entity.Utilisateur;
import edu.hightech.quiztech.repository.AuditLogRepository;
import edu.hightech.quiztech.repository.UtilisateurRepository;
import edu.hightech.quiztech.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditLogRepository auditLogRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public void logAction(Long utilisateurId, String action, String details) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setDetails(details);
        log.setDateAction(LocalDateTime.now());

        if (utilisateurId != null) {
            Utilisateur user = utilisateurRepository.findById(utilisateurId).orElse(null);
            log.setUtilisateur(user);
        }

        auditLogRepository.save(log);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditLogResponse> getAllLogs() {
        return auditLogRepository.findAll().stream().map(log -> {
            AuditLogResponse res = new AuditLogResponse();
            res.setId(log.getId());
            res.setAction(log.getAction());
            res.setDetails(log.getDetails());
            res.setDateAction(log.getDateAction());

            if (log.getUtilisateur() != null) {
                res.setUtilisateurId(log.getUtilisateur().getId());
                res.setUtilisateurNom(log.getUtilisateur().getNomComplet());
            }
            return res;
        }).collect(Collectors.toList());
    }
}