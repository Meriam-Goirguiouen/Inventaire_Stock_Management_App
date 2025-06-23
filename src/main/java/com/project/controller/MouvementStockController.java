package com.project.controller;

import com.project.model.MouvementStock;
import com.project.dto.MouvementStockDto;
import com.project.service.MouvementStockService; // Assurez-vous d'importer votre service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController // Indique que c'est un contrôleur qui renvoie des données (JSON)
@RequestMapping("/api/mouvements") // Toutes les URL de ce contrôleur commenceront par /api/mouvements
public class MouvementStockController {

    @Autowired
    private MouvementStockService mouvementStockService;

    // POST /api/mouvements/enregistrer
    @PostMapping("/enregistrer")
    public ResponseEntity<String> enregistrerMouvement(@RequestBody MouvementStockDto mouvementDto) {
        try {
            // On appelle la logique métier que vous avez déjà codée !
            mouvementStockService.enregistrerMouvement(mouvementDto);
            
            // Si tout se passe bien, on renvoie une réponse "OK" (code 200)
            return ResponseEntity.ok("Mouvement de stock enregistré avec succès.");

        } catch (Exception e) {
            // En cas d'erreur (ex: article non trouvé), on renvoie une erreur au frontend
            return ResponseEntity.badRequest().body("Erreur lors de l'enregistrement du mouvement : " + e.getMessage());
        }
    }
}