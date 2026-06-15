package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.dto.response.StatistiquesResponse;
import edu.hightech.quiztech.entity.Examen;
import edu.hightech.quiztech.entity.Submission;
import edu.hightech.quiztech.repository.ExamenRepository;
import edu.hightech.quiztech.service.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatServiceImpl implements StatService {

    private final ExamenRepository examenRepository;

    @Override
    @Transactional(readOnly = true)
    public StatistiquesResponse getStatistiquesExamen(Long examenId) {
        Examen examen = examenRepository.findById(examenId)
                .orElseThrow(() -> new RuntimeException("Examen introuvable"));

        StatistiquesResponse stats = new StatistiquesResponse();
        stats.setExamenId(examen.getId());
        stats.setExamenTitre(examen.getTitre());

        List<Submission> subs = examen.getSubmissions();
        if (subs == null || subs.isEmpty()) {
            stats.setNombreTotalSubmissions(0);
            stats.setMoyenneGenerale(0.0);
            stats.setNoteMaximale(0.0);
            stats.setNoteMinimale(0.0);
            stats.setRepartitionDesNotes(new HashMap<>());
            return stats;
        }

        int totalSubmissions = subs.size();
        double somme = 0.0;
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;

        for (Submission s : subs) {
            double note = s.getNote() != null ? s.getNote() : 0.0;
            somme += note;
            if (note > max) max = note;
            if (note < min) min = note;
        }

        stats.setNombreTotalSubmissions(totalSubmissions);
        stats.setMoyenneGenerale(somme / totalSubmissions);
        stats.setNoteMaximale(max);
        stats.setNoteMinimale(min);
        stats.setRepartitionDesNotes(new HashMap<>());

        return stats;
    }
}