package edu.hightech.quiztech.service;

import edu.hightech.quiztech.dto.response.StatistiquesResponse;

public interface StatService {
    StatistiquesResponse getStatistiquesExamen(Long examenId);
}