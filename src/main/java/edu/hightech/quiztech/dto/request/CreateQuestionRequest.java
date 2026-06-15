package edu.hightech.quiztech.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class CreateQuestionRequest {
    private String enonce;
    private Double note;
    private Integer ordre;
    private Integer nombreBonnesReponses;
    private Long examenId;
    private List<ChoixDto> choix;

    @Data
    public static class ChoixDto {
        private String texte;
        private Boolean estCorrect;
    }
}