package edu.hightech.quiztech.dto.request;

import lombok.Data;

@Data
public class TransferEtudiantRequest {
    private Long etudiantId;
    private Long ancienneClasseId;
    private Long nouvelleClasseId;
}