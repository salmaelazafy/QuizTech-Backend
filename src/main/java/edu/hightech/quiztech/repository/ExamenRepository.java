package edu.hightech.quiztech.repository;

import edu.hightech.quiztech.entity.Examen;

import edu.hightech.quiztech.entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamenRepository extends JpaRepository<Examen, Long> {


    List<Examen> findByEnseignant(Enseignant enseignant);


    List<Examen> findByTitreContainingIgnoreCase(String titre);
}