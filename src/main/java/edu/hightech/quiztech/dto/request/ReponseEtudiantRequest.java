package edu.hightech.quiztech.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class ReponseEtudiantRequest {
    private Long questionId;
    private List<Long> choixIds;
}