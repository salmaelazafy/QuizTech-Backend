package edu.hightech.quiztech.controller;

import edu.hightech.quiztech.dto.request.CreateClasseRequest;
import edu.hightech.quiztech.dto.response.ClasseResponse;
import edu.hightech.quiztech.service.ClasseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ClasseController {

    private final ClasseService classeService;

    @PostMapping
    public ResponseEntity<ClasseResponse> createClasse(
            @Valid @RequestBody CreateClasseRequest request) {

        return ResponseEntity.ok(
                classeService.createClasse(request)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClasseResponse> updateClasse(
            @PathVariable Long id,
            @Valid @RequestBody CreateClasseRequest request) {

        return ResponseEntity.ok(
                classeService.updateClasse(id, request)
        );
    }

    @GetMapping
    public ResponseEntity<List<ClasseResponse>> getAllClasses() {

        return ResponseEntity.ok(
                classeService.getAllClasses()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClasse(
            @PathVariable Long id) {

        classeService.deleteClasse(id);

        return ResponseEntity.ok("Classe supprimée avec succès");
    }
}