package edu.hightech.quiztech.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class QuestionResponse {
    private Long id;
    private String enonce;
    private Double note;
    private Integer ordre;
    private Integer nombreBonnesReponses;
    private Long examenId;
    private List<ChoixResponseDto> choix;

    @Data
    public static class ChoixResponseDto {
        private Long id;
        private String texte;
        private Boolean estCorrect;
    }
}