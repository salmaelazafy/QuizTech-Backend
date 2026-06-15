package edu.hightech.quiztech.dto.response;

import lombok.Data;

@Data
public class ClasseResponse {
    private Long id;
    private String nomClasse;
    private int nombreEtudiants;
    private int nombreExamens;
}