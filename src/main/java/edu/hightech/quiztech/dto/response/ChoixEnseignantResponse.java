package edu.hightech.quiztech.dto.response;

import lombok.Data;

@Data
public class ChoixEnseignantResponse {
    private Long id;
    private String texte;
    private Boolean estCorrect;
}