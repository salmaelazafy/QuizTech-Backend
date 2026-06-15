package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.dto.request.CreateClasseRequest;
import edu.hightech.quiztech.dto.response.ClasseResponse;
import edu.hightech.quiztech.entity.Classe;
import edu.hightech.quiztech.repository.ClasseRepository;
import edu.hightech.quiztech.service.ClasseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClasseServiceImpl implements ClasseService {

    private final ClasseRepository classeRepository;

    @Override
    @Transactional
    public ClasseResponse createClasse(CreateClasseRequest request) {
        Classe classe = new Classe();
        classe.setNomClasse(request.getNomClasse());

        Classe savedClasse = classeRepository.save(classe);
        return mapToResponse(savedClasse);
    }

    @Override
    @Transactional
    public ClasseResponse updateClasse(Long id, CreateClasseRequest request) {
        Classe classe = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe introuvable avec l'id : " + id));

        classe.setNomClasse(request.getNomClasse());

        Classe updatedClasse = classeRepository.save(classe);
        return mapToResponse(updatedClasse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClasseResponse> getAllClasses() {
        return classeRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteClasse(Long id) {
        if (!classeRepository.existsById(id)) {
            throw new RuntimeException("Classe introuvable avec l'id : " + id);
        }
        classeRepository.deleteById(id);
    }

    private ClasseResponse mapToResponse(Classe classe) {
        ClasseResponse response = new ClasseResponse();
        response.setId(classe.getId());
        response.setNomClasse(classe.getNomClasse());

        if (classe.getEtudiants() != null) {
            response.setNombreEtudiants(classe.getEtudiants().size());
        } else {
            response.setNombreEtudiants(0);
        }

        if (classe.getExamens() != null) {
            response.setNombreExamens(classe.getExamens().size());
        } else {
            response.setNombreExamens(0);
        }

        return response;
    }
}