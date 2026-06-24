package edu.hightech.quiztech.controller;

import edu.hightech.quiztech.dto.response.SubmissionResponse;
import edu.hightech.quiztech.service.CorrectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/corrections")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CorrectionController {

    private final CorrectionService correctionService;

    @PostMapping("/{submissionId}/auto")
    public ResponseEntity<SubmissionResponse> corrigerAutomatiquement(
            @PathVariable Long submissionId) {

        return ResponseEntity.ok(
                correctionService.corrigerAutomatiquement(submissionId)
        );
    }

    @PutMapping("/{submissionId}/manuel")
    public ResponseEntity<SubmissionResponse> ajusterNoteManuellement(
            @PathVariable Long submissionId,
            @RequestParam Double nouvelleNote,
            @RequestParam(required = false) String commentaire) {

        return ResponseEntity.ok(
                correctionService.ajusterNoteManuellement(
                        submissionId,
                        nouvelleNote,
                        commentaire
                )
        );
    }
}