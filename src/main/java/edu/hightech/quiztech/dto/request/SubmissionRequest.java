package edu.hightech.quiztech.dto.request;

import lombok.Data;
import java.util.List;

@Data
public class SubmissionRequest {
    private Long etudiantId;
    private Long examenId;
    private List<ReponseEtudiantRequest> reponses;
}