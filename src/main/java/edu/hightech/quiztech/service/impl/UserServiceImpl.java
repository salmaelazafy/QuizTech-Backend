package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.dto.request.CreateUserRequest;
import edu.hightech.quiztech.dto.request.UpdateUserRequest;
import edu.hightech.quiztech.dto.response.UserResponse;
import edu.hightech.quiztech.entity.*;
import edu.hightech.quiztech.repository.UtilisateurRepository;
import edu.hightech.quiztech.repository.ClasseRepository;
import edu.hightech.quiztech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UtilisateurRepository utilisateurRepository;
    private final ClasseRepository classeRepository;

    @Override
    @Transactional
    public UserResponse createUser(CreateUserRequest request) {
        Utilisateur user;

        switch (request.getRole()) {
            case ADMIN:
                user = new Admin();
                break;
            case ENSEIGNANT:
                user = new Enseignant();
                break;
            case ETUDIANT:
                Etudiant etudiant = new Etudiant();
                if (request.getClasseId() != null) {
                    Classe classe = classeRepository.findById(request.getClasseId())
                            .orElseThrow(() -> new RuntimeException("Classe introuvable avec l'id: " + request.getClasseId()));
                    etudiant.setClasse(classe);
                }
                user = etudiant;
                break;
            default:
                throw new IllegalArgumentException("Role non valide");
        }

        user.setNomComplet(request.getNomComplet());
        user.setEmail(request.getEmail());
        user.setMotDePasse(request.getMotDePasse());
        user.setPhotoProfil(request.getPhotoProfil());
        user.setRole(request.getRole());
        user.setDateInscription(LocalDate.now());
        user.setForcePasswordChange(false);

        Utilisateur savedUser = utilisateurRepository.save(user);
        return mapToResponse(savedUser);
    }

    @Override
    public UserResponse getUserById(Long id) {
        Utilisateur user = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'id: " + id));
        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return utilisateurRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UpdateUserRequest request) {
        Utilisateur user = utilisateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable avec l'id: " + id));

        user.setNomComplet(request.getNomComplet());
        user.setPhotoProfil(request.getPhotoProfil());
        if (request.getForcePasswordChange() != null) {
            user.setForcePasswordChange(request.getForcePasswordChange());
        }

        if (user instanceof Etudiant etudiant && request.getClasseId() != null) {
            Classe classe = classeRepository.findById(request.getClasseId())
                    .orElseThrow(() -> new RuntimeException("Classe introuvable"));
            etudiant.setClasse(classe);
        }

        Utilisateur updatedUser = utilisateurRepository.save(user);
        return mapToResponse(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        if (!utilisateurRepository.existsById(id)) {
            throw new RuntimeException("Utilisateur introuvable");
        }
        utilisateurRepository.deleteById(id);
    }

    private UserResponse mapToResponse(Utilisateur user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setNomComplet(user.getNomComplet());
        response.setEmail(user.getEmail());
        response.setPhotoProfil(user.getPhotoProfil());
        response.setDateInscription(user.getDateInscription());
        response.setLastActivity(user.getLastActivity());
        response.setForcePasswordChange(user.getForcePasswordChange());
        response.setRole(user.getRole());

        if (user instanceof Etudiant etudiant) {
            if (etudiant.getClasse() != null) {
                response.setClasseId(etudiant.getClasse().getId());
                response.setClasseNom(etudiant.getClasse().getNomClasse());
            }
        }
        return response;
    }
}