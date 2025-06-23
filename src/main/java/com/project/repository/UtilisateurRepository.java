package com.project.repository;

import com.project.model.Utilisateur;
import com.project.model.Utilisateur.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    List<Utilisateur> findByRole(Role role);
    Utilisateur findByEmail(String email); 
    long countByRole(Utilisateur.Role role);
    long count();
}
