package edu.hightech.quiztech.dto.response;

import lombok.Data;

@Data
public class ChoixResponse {
    private Long id;
    private String texte;
    private Boolean estCorrect;
}