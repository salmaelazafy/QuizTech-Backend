package edu.hightech.quiztech.controller;

import edu.hightech.quiztech.dto.request.CreateRessourceRequest;
import edu.hightech.quiztech.dto.request.UpdateRessourceRequest;
import edu.hightech.quiztech.dto.response.RessourceResponse;
import edu.hightech.quiztech.service.RessourcePedagogiqueService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ressources")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RessourcePedagogiqueController {

    private final RessourcePedagogiqueService ressourceService;

    @PostMapping
    public ResponseEntity<RessourceResponse> createRessource(
            @Valid @RequestBody CreateRessourceRequest request) {

        return ResponseEntity.ok(
                ressourceService.createRessource(request)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<RessourceResponse> updateRessource(
            @PathVariable Long id,
            @Valid @RequestBody UpdateRessourceRequest request) {

        return ResponseEntity.ok(
                ressourceService.updateRessource(id, request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<RessourceResponse> getRessourceById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                ressourceService.getRessourceById(id)
        );
    }

    @GetMapping("/classe/{classeId}")
    public ResponseEntity<List<RessourceResponse>> getRessourcesByClasse(
            @PathVariable Long classeId) {

        return ResponseEntity.ok(
                ressourceService.getRessourcesByClasse(classeId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRessource(
            @PathVariable Long id) {

        ressourceService.deleteRessource(id);

        return ResponseEntity.ok("Ressource supprimée avec succès");
    }
}