package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.config.jwt.JwtUtil;
import edu.hightech.quiztech.dto.request.CreateUserRequest;
import edu.hightech.quiztech.dto.request.LoginRequest;
import edu.hightech.quiztech.dto.response.AuthResponse;
import edu.hightech.quiztech.entity.Admin;
import edu.hightech.quiztech.entity.Classe;
import edu.hightech.quiztech.entity.Enseignant;
import edu.hightech.quiztech.entity.Etudiant;
import edu.hightech.quiztech.entity.Utilisateur;
import edu.hightech.quiztech.repository.AdminRepository;
import edu.hightech.quiztech.repository.ClasseRepository;
import edu.hightech.quiztech.repository.EnseignantRepository;
import edu.hightech.quiztech.repository.EtudiantRepository;
import edu.hightech.quiztech.repository.UtilisateurRepository;
import edu.hightech.quiztech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final AdminRepository adminRepository;
    private final EnseignantRepository enseignantRepository;
    private final EtudiantRepository etudiantRepository;
    private final ClasseRepository classeRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse login(LoginRequest request) {

        Utilisateur user = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Email ou mot de passe incorrect"));

        if (!passwordEncoder.matches(
                request.getMotDePasse(),
                user.getMotDePasse())) {

            throw new RuntimeException(
                    "Email ou mot de passe incorrect");
        }

        String role = user.getRole().name();

        String token = jwtUtil.generateToken(
                user.getEmail(),
                role
        );

        return new AuthResponse(
                token,
                user.getEmail(),
                user.getNomComplet(),
                role
        );
    }

    @Override
    public AuthResponse register(CreateUserRequest request) {

        if (utilisateurRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException(
                    "Cet email est déjà utilisé");
        }

        Utilisateur utilisateur;

        switch (request.getRole()) {

            case ADMIN -> {
                utilisateur = new Admin();
            }

            case ENSEIGNANT -> {
                utilisateur = new Enseignant();
            }

            case ETUDIANT -> {

                Etudiant etudiant = new Etudiant();

                if (request.getClasseId() != null) {

                    Classe classe = classeRepository.findById(
                                    request.getClasseId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Classe introuvable"));

                    etudiant.setClasse(classe);
                }

                utilisateur = etudiant;
            }

            default -> throw new RuntimeException("Role invalide");
        }

        utilisateur.setNomComplet(request.getNomComplet());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setPhotoProfil(request.getPhotoProfil());
        utilisateur.setRole(request.getRole());
        utilisateur.setDateInscription(LocalDate.now());
        utilisateur.setForcePasswordChange(false);

        utilisateur.setMotDePasse(
                passwordEncoder.encode(
                        request.getMotDePasse()
                )
        );

        if (utilisateur instanceof Admin admin) {
            adminRepository.save(admin);
        } else if (utilisateur instanceof Enseignant enseignant) {
            enseignantRepository.save(enseignant);
        } else if (utilisateur instanceof Etudiant etudiant) {
            etudiantRepository.save(etudiant);
        }

        String token = jwtUtil.generateToken(
                utilisateur.getEmail(),
                utilisateur.getRole().name()
        );

        return new AuthResponse(
                token,
                utilisateur.getEmail(),
                utilisateur.getNomComplet(),
                utilisateur.getRole().name()
        );
    }
}