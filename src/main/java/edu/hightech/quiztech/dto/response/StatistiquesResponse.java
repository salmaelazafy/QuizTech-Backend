package edu.hightech.quiztech.dto.response;

import lombok.Data;
import java.util.Map;

@Data
public class StatistiquesResponse {
    private Long examenId;
    private String examenTitre;
    private int nombreTotalSubmissions;
    private Double moyenneGenerale;
    private Double noteMaximale;
    private Double noteMinimale;
    private Map<String, Integer> repartitionDesNotes;
}