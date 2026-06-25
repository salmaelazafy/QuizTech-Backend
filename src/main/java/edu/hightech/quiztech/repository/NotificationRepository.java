package edu.hightech.quiztech.repository;

import edu.hightech.quiztech.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

   
    List<Notification> findByUtilisateurIdOrderByDateNotificationDesc(Long utilisateurId);

   
    List<Notification> findByUtilisateurIdAndEstLueFalseOrderByDateNotificationDesc(Long utilisateurId);

   
    long countByUtilisateurIdAndEstLueFalse(Long utilisateurId);

    
    @Modifying
    @Query("UPDATE Notification n SET n.estLue = true WHERE n.utilisateur.id = :utilisateurId")
    void marquerToutesCommeLues(@Param("utilisateurId") Long utilisateurId);

    
    List<Notification> findByUtilisateurIdAndTypeOrderByDateNotificationDesc(Long utilisateurId, String type);
}