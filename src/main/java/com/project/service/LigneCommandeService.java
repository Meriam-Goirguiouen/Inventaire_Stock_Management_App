package com.project.service;

import com.project.model.LigneCommande;
import com.project.repository.LigneCommandeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LigneCommandeService {

    private final LigneCommandeRepository ligneCommandeRepository;

    public LigneCommandeService(LigneCommandeRepository ligneCommandeRepository) {
        this.ligneCommandeRepository = ligneCommandeRepository;
    }

    // Créer ou modifier une ligne de commande
    public LigneCommande save(LigneCommande ligne) {
        return ligneCommandeRepository.save(ligne);
    }

    // Lire une ligne de commande par ID
    public Optional<LigneCommande> findById(Long id) {
        return ligneCommandeRepository.findById(id);
    }

    // Supprimer une ligne de commande
    public void delete(Long id) {
        ligneCommandeRepository.deleteById(id);
    }

    // Obtenir toutes les lignes de commande
    public List<LigneCommande> findAll() {
        return ligneCommandeRepository.findAll();
    }
}
