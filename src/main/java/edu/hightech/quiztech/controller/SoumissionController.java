package edu.hightech.quiztech.controller;

import edu.hightech.quiztech.dto.request.SubmissionRequest;
import edu.hightech.quiztech.dto.response.SubmissionResponse;
import edu.hightech.quiztech.service.SoumissionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/soumissions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class SoumissionController {

    private final SoumissionService soumissionService;

    @PostMapping
    public ResponseEntity<SubmissionResponse> soumettreExamen(
            @Valid @RequestBody SubmissionRequest request) {

        return ResponseEntity.ok(
                soumissionService.soumettreExamen(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubmissionResponse> getSoumissionById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                soumissionService.getSoumissionById(id)
        );
    }

    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<SubmissionResponse>> getSoumissionsByEtudiant(
            @PathVariable Long etudiantId) {

        return ResponseEntity.ok(
                soumissionService.getSoumissionsByEtudiant(etudiantId)
        );
    }
}