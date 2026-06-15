package edu.hightech.quiztech.service;

import edu.hightech.quiztech.dto.request.CreateUserRequest;
import edu.hightech.quiztech.dto.request.UpdateUserRequest;
import edu.hightech.quiztech.dto.response.UserResponse;
import java.util.List;

public interface UserService {
    UserResponse createUser(CreateUserRequest request);
    UserResponse updateUser(Long id, UpdateUserRequest request);
    UserResponse getUserById(Long id);
    List<UserResponse> getAllUsers();
    void deleteUser(Long id);
}