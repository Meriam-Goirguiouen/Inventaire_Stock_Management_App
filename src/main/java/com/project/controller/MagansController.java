package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.model.Article;
import com.project.model.BonCommande;
import com.project.model.Utilisateur;
import com.project.repository.BonCommandeRepository;
import com.project.repository.StockRepository;
import com.project.service.ArticleService;
import java.util.List;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/magasinier")
public class MagansController {
      @Autowired
    private StockRepository stockRepository;

    // Assurez-vous d'avoir aussi un repository pour les commandes
    @Autowired
    private BonCommandeRepository bonCommandeRepository;

    @Autowired
    private ArticleService articleService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {

        // --- PARTIE À MODIFIER / AJOUTER ---

        // 1. Calculer le nombre d'articles en stock faible
        long lowStockCount = stockRepository.countLowStockItems();

        // 2. Calculer le nombre de commandes en attente (EN BONUS !)
        // long pendingOrdersCount = bonCommandeRepository.countByEtat(BonCommande.EtatCommande.EN_ATTENTE);
        
        // 3. Récupérer le nom de l'utilisateur connecté (EN BONUS !)
        // Supposons que vous stockez l'objet Utilisateur dans la session au moment du login
        Utilisateur currentUser = (Utilisateur) session.getAttribute("utilisateur");
        String magasinierName = (currentUser != null) ? currentUser.getPrenom() : "Magasinier";

        // 4. Ajouter ces informations au modèle pour que la page HTML puisse les lire
        model.addAttribute("stockLowCount", lowStockCount);
        // model.addAttribute("pendingOrdersCount", pendingOrdersCount);
         model.addAttribute("magasinierName", magasinierName);
        

        return "dashboard";
    }

      @GetMapping("/articles")
    public String listeArticles(Model model) {
        // C'est ici qu'on ajoute la logique de l'autre contrôleur
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "gestionarticle";
    }

    @GetMapping("/alertes")
    public String alertesStock(Model model) {
        // Ajouter les produits dont le stock est bas
        return "alertes";
    }

    @GetMapping("/commande")
    public String formulaireCommande(Model model) {
        // Préparer le formulaire de commande
        return "Magasiniercommande";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "login";
    }
}