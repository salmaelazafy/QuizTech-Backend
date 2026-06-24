package edu.hightech.quiztech.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity
	@Table(name = "admins")
	@DiscriminatorValue("ADMIN")
	@Getter @Setter @NoArgsConstructor
		 
public class Admin extends Utilisateur {
	
	
}

