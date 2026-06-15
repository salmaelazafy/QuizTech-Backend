package edu.hightech.quiztech.service.impl;

import edu.hightech.quiztech.dto.request.CreateUserRequest;
import edu.hightech.quiztech.dto.request.LoginRequest;
import edu.hightech.quiztech.dto.response.AuthResponse;
import edu.hightech.quiztech.entity.Utilisateur;
import edu.hightech.quiztech.repository.UtilisateurRepository;
import edu.hightech.quiztech.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UtilisateurRepository utilisateurRepository;

    @Override
    public AuthResponse login(LoginRequest request) {
        Utilisateur user = utilisateurRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

        String userRole = user.getRole() != null ? user.getRole().name() : "USER";

        return new AuthResponse(
                "MOCK_JWT_TOKEN",
                user.getEmail(),
                user.getNomComplet(),
                userRole
        );
    }

    @Override
    public AuthResponse register(CreateUserRequest request) {
        String roleStr = request.getRole() != null ? request.getRole().toString() : "USER";

        return new AuthResponse(
                "MOCK_REGISTRATION_TOKEN",
                request.getEmail(),
                request.getNomComplet(),
                roleStr
        );
    }
}