package edu.hightech.quiztech.repository;

import edu.hightech.quiztech.entity.Examen;
import edu.hightech.quiztech.entity.Etudiant;
import edu.hightech.quiztech.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByEtudiant(Etudiant etudiant);

    List<Submission> findByExamen(Examen examen);

    Optional<Submission> findByEtudiantAndExamen(Etudiant etudiant, Examen examen);

    List<Submission> findByIsGraded(Boolean isGraded);
}