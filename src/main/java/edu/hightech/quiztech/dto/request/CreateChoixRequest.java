package edu.hightech.quiztech.dto.request;

import lombok.Data;

@Data
public class CreateChoixRequest {
    private String texte;
    private Boolean estCorrect;
}