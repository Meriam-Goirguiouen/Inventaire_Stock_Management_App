package com.project.repository;

import com.project.model.BonCommande;
import com.project.model.Utilisateur;
import com.project.model.BonCommande.EtatCommande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BonCommandeRepository extends JpaRepository<BonCommande, Long> {
    List<BonCommande> findByFournisseur(Utilisateur fournisseur);
    List<BonCommande> findByEtat(EtatCommande etat);
}
