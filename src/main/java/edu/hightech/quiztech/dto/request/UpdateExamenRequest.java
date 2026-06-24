package edu.hightech.quiztech.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateExamenRequest {

    private String titre;

    private String description;

    @Min(value = 1, message = "La durée doit être supérieure à 0")
    private Integer duree;

    @Min(value = 1, message = "Le coefficient doit être supérieur à 0")
    private Integer coefficient;

    private LocalDateTime dateOuverture;

    private LocalDateTime dateFermeture;

    private List<Long> classeIds;
}