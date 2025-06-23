package com.project.service;

import com.project.model.BonCommande;
import com.project.model.Utilisateur;
import com.project.repository.BonCommandeRepository;
import com.project.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BonCommandeService {

    @Autowired
    private BonCommandeRepository bonCommandeRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    public List<BonCommande> getAllBonCommandes() {
        return bonCommandeRepository.findAll();
    }

    public BonCommande getBonCommandeById(Long id) {
        return bonCommandeRepository.findById(id).orElse(null);
    }

    public List<BonCommande> getBonCommandesByFournisseur(Utilisateur fournisseur) {
        return bonCommandeRepository.findByFournisseur(fournisseur);
    }

    public BonCommande createBonCommande(BonCommande commande) {
        // Vérifie que le fournisseur est bien un utilisateur de rôle FOURNISSEUR
        if (commande.getFournisseur().getRole() != Utilisateur.Role.FOURNISSEUR) {
            throw new IllegalArgumentException("Ce n'est pas un fournisseur valide !");
        }

        commande.setDateCreation(LocalDateTime.now());
        return bonCommandeRepository.save(commande);
    }

    public BonCommande updateEtatCommande(Long id, BonCommande.EtatCommande nouvelEtat) {
        BonCommande commande = getBonCommandeById(id);
        if (commande != null) {
            commande.setEtat(nouvelEtat);
            return bonCommandeRepository.save(commande);
        }
        return null;
    }

    public void deleteBonCommande(Long id) {
        bonCommandeRepository.deleteById(id);
    }
    
    public BonCommande saveBonCommande(BonCommande commande) {
        return bonCommandeRepository.save(commande);
    }
    
    public List<BonCommande> getLivreeOrderByFournisseur(Utilisateur fournisseur){
        List<BonCommande> orders = bonCommandeRepository.findByFournisseurAndEtat(fournisseur, BonCommande.EtatCommande.LIVREE);
        return orders;
    }
    
    public long countCommandes() {
        return bonCommandeRepository.count();
    }
    
    
}
