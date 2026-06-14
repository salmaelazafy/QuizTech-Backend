package edu.hightech.quiztech.entity;

import edu.hightech.quiztech.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "utilisateurs")
@Data      
@NoArgsConstructor  
@AllArgsConstructor 
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type_utilisateur")

public abstract class  Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    private String nomComplet;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String motDePasse; 
    
    private String photoProfil;

    private LocalDate dateInscription;


    private LocalDateTime lastActivity;

    private Boolean forcePasswordChange = false;

    private String temporaryPassword;
    
    @Enumerated(EnumType.STRING)
    private Role role;

   

    @OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
    private List<AuditLog> auditLogs;
}