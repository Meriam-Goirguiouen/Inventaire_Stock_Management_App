package com.example.inventaire.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class magasinierController {

    @GetMapping("/admin/produits")
    public String afficherPagemagasinier() {
        return "magasinier"; // Correspond au fichier produit.html dans templates/
    }
}
