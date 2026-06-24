package edu.hightech.quiztech.controller;

import edu.hightech.quiztech.dto.response.StatistiquesResponse;
import edu.hightech.quiztech.service.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stats")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StatsController {

    private final StatService statService;

    @GetMapping("/examen/{examenId}")
    public ResponseEntity<StatistiquesResponse> getStatistiquesExamen(
            @PathVariable Long examenId) {

        return ResponseEntity.ok(
                statService.getStatistiquesExamen(examenId)
        );
    }
}