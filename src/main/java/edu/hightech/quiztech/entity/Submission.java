package edu.hightech.quiztech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "submissions")
@Getter
@Setter
@NoArgsConstructor

public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateDebut;

    private LocalDateTime dateSoumission;

    private Double note;

    private String commentaire;

    private Boolean isGraded = false;

    @Column(columnDefinition = "TEXT")
    private String antiFraudDetails;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "examen_id")
    private Examen examen;

    @OneToMany(mappedBy = "submission" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReponseEtudiant> reponses;
}