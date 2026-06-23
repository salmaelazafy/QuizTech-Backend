package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.dto.request.CreateExamenRequest;
import edu.hightech.quiztech.dto.request.UpdateExamenRequest;
import edu.hightech.quiztech.dto.response.ExamenResponse;
import edu.hightech.quiztech.entity.Examen;
import edu.hightech.quiztech.entity.Classe;
import edu.hightech.quiztech.entity.Enseignant;
import edu.hightech.quiztech.entity.Utilisateur;
import edu.hightech.quiztech.repository.ExamenRepository;
import edu.hightech.quiztech.repository.ClasseRepository;
import edu.hightech.quiztech.repository.UtilisateurRepository;
import edu.hightech.quiztech.service.ExamenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamenServiceImpl implements ExamenService {

    private final ExamenRepository examenRepository;
    private final ClasseRepository classeRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public ExamenResponse createExamen(CreateExamenRequest request) {
        Examen examen = new Examen();
        examen.setTitre(request.getTitre());
        examen.setDescription(request.getDescription());
        examen.setDuree(request.getDuree());
        examen.setCoefficient(request.getCoefficient());
        examen.setDateOuverture(request.getDateOuverture());
        examen.setDateFermeture(request.getDateFermeture());

        if (request.getClasseIds() != null && !request.getClasseIds().isEmpty()) {
            List<Classe> classes = classeRepository.findAllById(request.getClasseIds());
            examen.setClasses(classes);
        }

        Examen savedExamen = examenRepository.save(examen);
        return mapToResponse(savedExamen);
    }

    @Override
    @Transactional
    public ExamenResponse updateExamen(Long id, UpdateExamenRequest request) {
        Examen examen = examenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Examen introuvable avec l'id : " + id));

        examen.setTitre(request.getTitre());
        examen.setDescription(request.getDescription());
        examen.setDuree(request.getDuree());
        examen.setCoefficient(request.getCoefficient());
        examen.setDateOuverture(request.getDateOuverture());
        examen.setDateFermeture(request.getDateFermeture());

        if (request.getClasseIds() != null) {
            List<Classe> classes = classeRepository.findAllById(request.getClasseIds());
            examen.setClasses(classes);
        }

        Examen updatedExamen = examenRepository.save(examen);
        return mapToResponse(updatedExamen);
    }

    @Override
    @Transactional(readOnly = true)
    public ExamenResponse getExamenById(Long id) {
        Examen examen = examenRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Examen introuvable"));
        return mapToResponse(examen);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamenResponse> getAllExamens() {
        return examenRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExamenResponse> getExamensByClasse(Long classeId) {
        return examenRepository.findAll().stream()
                .filter(examen -> examen.getClasses() != null &&
                        examen.getClasses().stream().anyMatch(classe -> classe.getId().equals(classeId)))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteExamen(Long id) {
        if (!examenRepository.existsById(id)) {
            throw new RuntimeException("Examen introuvable");
        }
        examenRepository.deleteById(id);
    }

    private ExamenResponse mapToResponse(Examen examen) {
        ExamenResponse res = new ExamenResponse();
        res.setId(examen.getId());
        res.setTitre(examen.getTitre());
        res.setDescription(examen.getDescription());
        res.setDuree(examen.getDuree());
        res.setCoefficient(examen.getCoefficient());
        res.setDateOuverture(examen.getDateOuverture());
        res.setDateFermeture(examen.getDateFermeture());

        if (examen.getEnseignant() != null) {
            res.setEnseignantNom(examen.getEnseignant().getNomComplet());
        }

        res.setNombreQuestions(examen.getQuestions() != null ? examen.getQuestions().size() : 0);
        res.setNombreSubmissions(examen.getSubmissions() != null ? examen.getSubmissions().size() : 0);

        return res;
    }
}