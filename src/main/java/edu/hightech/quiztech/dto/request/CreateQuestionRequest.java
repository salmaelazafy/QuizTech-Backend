package edu.hightech.quiztech.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateQuestionRequest {

    @NotBlank(message = "L'énoncé est obligatoire")
    private String enonce;

    @NotNull(message = "La note est obligatoire")
    @Min(value = 1, message = "La note doit être supérieure à 0")
    private Double note;

    @NotNull(message = "L'ordre est obligatoire")
    private Integer ordre;

    @NotNull(message = "Le nombre de bonnes réponses est obligatoire")
    private Integer nombreBonnesReponses;

    @NotNull(message = "L'examen est obligatoire")
    private Long examenId;

    private List<ChoixDto> choix;

    @Data
    public static class ChoixDto {

        @NotBlank(message = "Le texte du choix est obligatoire")
        private String texte;

        private Boolean estCorrect;
    }
}