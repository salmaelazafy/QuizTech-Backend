package edu.hightech.quiztech.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateClasseRequest {

    @NotBlank(message = "Le nom de la classe est obligatoire")
    private String nomClasse;
}