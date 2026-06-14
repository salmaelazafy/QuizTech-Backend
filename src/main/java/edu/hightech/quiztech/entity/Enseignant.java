package edu.hightech.quiztech.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "enseignants")
@DiscriminatorValue("ENSEIGNANT")
@Getter @Setter @NoArgsConstructor

public class Enseignant extends Utilisateur {



    @OneToMany(mappedBy = "enseignant")
    private List<Examen> examens;

    @OneToMany(mappedBy = "enseignant")
    private List<RessourcePedagogique> ressources;

   }