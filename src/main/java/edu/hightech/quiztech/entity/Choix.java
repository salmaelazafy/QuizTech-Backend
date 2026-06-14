package edu.hightech.quiztech.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "choix")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Choix {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String texte;
    
    private Boolean estCorrect = false;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;
    
    @OneToMany(mappedBy = "choix")
    private List<ReponseEtudiant> reponses;
    
}