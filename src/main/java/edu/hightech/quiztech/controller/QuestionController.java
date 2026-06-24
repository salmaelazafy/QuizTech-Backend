package edu.hightech.quiztech.controller;

import edu.hightech.quiztech.dto.request.CreateQuestionRequest;
import edu.hightech.quiztech.dto.response.QuestionResponse;
import edu.hightech.quiztech.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class QuestionController {

    private final QuestionService questionService;

    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(
            @Valid @RequestBody CreateQuestionRequest request) {

        return ResponseEntity.ok(
                questionService.createQuestion(request)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionResponse> getQuestionById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                questionService.getQuestionById(id)
        );
    }

    @GetMapping("/examen/{examenId}")
    public ResponseEntity<List<QuestionResponse>> getQuestionsByExamen(
            @PathVariable Long examenId) {

        return ResponseEntity.ok(
                questionService.getQuestionsByExamen(examenId)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(
            @PathVariable Long id) {

        questionService.deleteQuestion(id);

        return ResponseEntity.ok(
                "Question supprimée avec succès"
        );
    }
}