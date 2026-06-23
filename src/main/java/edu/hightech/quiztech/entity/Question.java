package edu.hightech.quiztech.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "questions")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_question")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public  class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String enonce;

    private Double note;

    private Integer ordre;
    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Choix> choix;

    @Column(nullable = false)
    private Integer nombreBonnesReponses;

    @ManyToOne
    @JoinColumn(name = "examen_id")
    private Examen examen;
    
    @OneToMany(mappedBy = "question")
    private List<ReponseEtudiant> reponseEtudiants;
    
}
