package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.dto.request.CreateQuestionRequest;
import edu.hightech.quiztech.dto.response.QuestionResponse;
import edu.hightech.quiztech.entity.Question;
import edu.hightech.quiztech.entity.Choix;
import edu.hightech.quiztech.entity.Examen;
import edu.hightech.quiztech.repository.QuestionRepository;
import edu.hightech.quiztech.repository.ExamenRepository;
import edu.hightech.quiztech.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ExamenRepository examenRepository;

    @Override
    @Transactional
    public QuestionResponse createQuestion(CreateQuestionRequest request) {
        Examen examen = examenRepository.findById(request.getExamenId())
                .orElseThrow(() -> new RuntimeException("Examen introuvable"));

        Question question = new Question() {};

        question.setEnonce(request.getEnonce());
        question.setNote(request.getNote());
        question.setOrdre(request.getOrdre());
        question.setNombreBonnesReponses(request.getNombreBonnesReponses());
        question.setExamen(examen);

        if (request.getChoix() != null) {
            List<Choix> choixList = new ArrayList<>();
            for (CreateQuestionRequest.ChoixDto cDto : request.getChoix()) {
                Choix choix = new Choix();
                choix.setTexte(cDto.getTexte());
                choix.setEstCorrect(cDto.getEstCorrect() != null ? cDto.getEstCorrect() : false);
                choix.setQuestion(question);
                choixList.add(choix);
            }
            question.setChoix(choixList);
        }

        Question savedQuestion = questionRepository.save(question);
        return mapToResponse(savedQuestion);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionResponse getQuestionById(Long id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question introuvable"));
        return mapToResponse(question);
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionResponse> getQuestionsByExamen(Long examenId) {
        return questionRepository.findAll().stream()
                .filter(q -> q.getExamen() != null && q.getExamen().getId().equals(examenId))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteQuestion(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new RuntimeException("Question introuvable");
        }
        questionRepository.deleteById(id);
    }

    private QuestionResponse mapToResponse(Question question) {
        QuestionResponse res = new QuestionResponse();
        res.setId(question.getId());
        res.setEnonce(question.getEnonce());
        res.setNote(question.getNote());
        res.setOrdre(question.getOrdre());
        res.setNombreBonnesReponses(question.getNombreBonnesReponses());

        if (question.getExamen() != null) {
            res.setExamenId(question.getExamen().getId());
        }

        if (question.getChoix() != null) {
            List<QuestionResponse.ChoixResponseDto> choixDtos = question.getChoix().stream()
                    .map(c -> {
                        QuestionResponse.ChoixResponseDto dto = new QuestionResponse.ChoixResponseDto();
                        dto.setId(c.getId());
                        dto.setTexte(c.getTexte());
                        dto.setEstCorrect(c.getEstCorrect());
                        return dto;
                    }).collect(Collectors.toList());
            res.setChoix(choixDtos);
        }

        return res;
    }
}