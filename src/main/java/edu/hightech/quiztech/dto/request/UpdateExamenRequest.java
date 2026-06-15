package edu.hightech.quiztech.dto.request;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UpdateExamenRequest {
    private String titre;
    private String description;
    private Integer duree;
    private Integer coefficient;
    private LocalDateTime dateOuverture;
    private LocalDateTime dateFermeture;
    private List<Long> classeIds;
}