package com.project.service;

import com.project.model.Alerte;
import com.project.model.Produit;
import com.project.repository.AlerteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlerteService {

    @Autowired
    private AlerteRepository alerteRepository;

    public List<Alerte> getAllAlertes() {
        return alerteRepository.findAll();
    }

    public Alerte saveAlerte(Alerte alerte) {
        return alerteRepository.save(alerte);
    }

    public List<Alerte> getAlertesByProduit(Produit produit) {
        return alerteRepository.findByProduit(produit);
    }

    public void genererAlerteSiCritique(Produit produit) {
        if (produit.getQuantite() <= produit.getSeuilCritique()) {
            Alerte alerte = new Alerte();
            alerte.setProduit(produit);
            alerte.setDateSend(LocalDateTime.now());
            alerte.setMessage("Stock critique pour le produit : " + produit.getNom());
            alerteRepository.save(alerte);
        }
    }
}
