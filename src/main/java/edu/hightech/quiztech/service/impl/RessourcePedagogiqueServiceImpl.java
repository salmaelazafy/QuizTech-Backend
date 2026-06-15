package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.dto.request.CreateRessourceRequest;
import edu.hightech.quiztech.dto.request.UpdateRessourceRequest;
import edu.hightech.quiztech.dto.response.RessourceResponse;
import edu.hightech.quiztech.entity.RessourcePedagogique;
import edu.hightech.quiztech.entity.Classe;
import edu.hightech.quiztech.entity.Enseignant;
import edu.hightech.quiztech.entity.Utilisateur;
import edu.hightech.quiztech.repository.RessourcePedagogiqueRepository;
import edu.hightech.quiztech.repository.ClasseRepository;
import edu.hightech.quiztech.repository.UtilisateurRepository;
import edu.hightech.quiztech.service.RessourcePedagogiqueService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RessourcePedagogiqueServiceImpl implements RessourcePedagogiqueService {

    private final RessourcePedagogiqueRepository ressourceRepository;
    private final ClasseRepository classeRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Override
    @Transactional
    public RessourceResponse createRessource(CreateRessourceRequest request) {
        RessourcePedagogique ressource = new RessourcePedagogique();
        ressource.setTitre(request.getTitre());
        ressource.setDescription(request.getDescription());
        ressource.setFichier(request.getFichier());

        ressource.setDatePublication(request.getDatePublication() != null ?
                request.getDatePublication() : LocalDateTime.now());

        if (request.getClasseId() != null) {
            Classe classe = classeRepository.findById(request.getClasseId())
                    .orElseThrow(() -> new RuntimeException("Classe introuvable"));
            ressource.setClasse(classe);
        }

        if (request.getEnseignantId() != null) {
            Utilisateur utilisateur = utilisateurRepository.findById(request.getEnseignantId())
                    .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));
            if (utilisateur instanceof Enseignant) {
                ressource.setEnseignant((Enseignant) utilisateur);
            }
        }

        RessourcePedagogique saved = ressourceRepository.save(ressource);
        return mapToResponse(saved);
    }

    @Override
    @Transactional
    public RessourceResponse updateRessource(Long id, UpdateRessourceRequest request) {
        RessourcePedagogique ressource = ressourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ressource introuvable"));

        ressource.setTitre(request.getTitre());
        ressource.setDescription(request.getDescription());
        ressource.setFichier(request.getFichier());

        if (request.getClasseId() != null) {
            Classe classe = classeRepository.findById(request.getClasseId())
                    .orElseThrow(() -> new RuntimeException("Classe introuvable"));
            ressource.setClasse(classe);
        }

        RessourcePedagogique updated = ressourceRepository.save(ressource);
        return mapToResponse(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public RessourceResponse getRessourceById(Long id) {
        RessourcePedagogique ressource = ressourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ressource introuvable"));
        return mapToResponse(ressource);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RessourceResponse> getRessourcesByClasse(Long classeId) {
        return ressourceRepository.findAll().stream()
                .filter(r -> r.getClasse() != null && r.getClasse().getId().equals(classeId))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteRessource(Long id) {
        if (!ressourceRepository.existsById(id)) {
            throw new RuntimeException("Ressource introuvable");
        }
        ressourceRepository.deleteById(id);
    }

    private RessourceResponse mapToResponse(RessourcePedagogique ressource) {
        RessourceResponse res = new RessourceResponse();
        res.setId(ressource.getId());
        res.setTitre(ressource.getTitre());
        res.setDescription(ressource.getDescription());
        res.setFichier(ressource.getFichier());
        res.setDatePublication(ressource.getDatePublication());

        if (ressource.getClasse() != null) {
            res.setClasseId(ressource.getClasse().getId());
            res.setNomClasse(ressource.getClasse().getNomClasse());
        }

        if (ressource.getEnseignant() != null) {
            res.setEnseignantNom(ressource.getEnseignant().getNomComplet());
        }

        return res;
    }
}