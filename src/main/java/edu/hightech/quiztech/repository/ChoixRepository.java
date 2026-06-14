package edu.hightech.quiztech.repository;

import edu.hightech.quiztech.entity.Choix;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChoixRepository extends JpaRepository<Choix, Long> {

    List<Choix> findByQuestionId(Long questionId);

}