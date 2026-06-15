package edu.hightech.quiztech.service;

import edu.hightech.quiztech.dto.request.CreateUserRequest;
import edu.hightech.quiztech.dto.request.LoginRequest;
import edu.hightech.quiztech.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(CreateUserRequest request);
}