package edu.hightech.quiztech.controller;

import edu.hightech.quiztech.dto.request.CreateExamenRequest;
import edu.hightech.quiztech.dto.request.UpdateExamenRequest;
import edu.hightech.quiztech.dto.response.ExamenResponse;
import edu.hightech.quiztech.service.ExamenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examens")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ExamenController {

    private final ExamenService examenService;

    @PostMapping
    public ResponseEntity<ExamenResponse> createExamen(
            @Valid @RequestBody CreateExamenRequest request) {

        return ResponseEntity.ok(
                examenService.createExamen(request)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamenResponse> updateExamen(
            @PathVariable Long id,
            @Valid @RequestBody UpdateExamenRequest request) {

        return ResponseEntity.ok(
                examenService.updateExamen(id, request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamenResponse> getExamenById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                examenService.getExamenById(id)
        );
    }

    @GetMapping
    public ResponseEntity<List<ExamenResponse>> getAllExamens() {

        return ResponseEntity.ok(
                examenService.getAllExamens()
        );
    }

    @GetMapping("/classe/{classeId}")
    public ResponseEntity<List<ExamenResponse>> getExamensByClasse(
            @PathVariable Long classeId) {

        return ResponseEntity.ok(
                examenService.getExamensByClasse(classeId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExamen(
            @PathVariable Long id) {

        examenService.deleteExamen(id);

        return ResponseEntity.ok(
                "Examen supprimé avec succès"
        );
    }
}