package edu.hightech.quiztech.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reponses_etudiants")
@Getter
@Setter
@NoArgsConstructor

public class ReponseEtudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double noteObtenue;
    
    @ManyToOne
    @JoinColumn(name = "choix_id")
    private Choix choix;

    @ManyToOne
    @JoinColumn(name = "submission_id")
    private Submission submission;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
}