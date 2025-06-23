
package com.project.controller;

import com.project.dto.MouvementStockDto;
import com.project.model.Article;
import com.project.repository.ArticleRepository;
import com.project.service.MouvementStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/magasinier/articles")
public class ArticleFormController { // <-- Votre nom de contrôleur

    @Autowired
    private MouvementStockService mouvementStockService;

    @Autowired
    private ArticleRepository articleRepository;

    // Cette méthode affiche la page de gestion des articles
    /*@GetMapping
    public String showGestionArticlesPage(Model model) {
        // Pour remplir la liste des articles dans le tableau et le select
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articles", articleList);
        
       
        return "gestionarticle"; // <-- Décommentez cette ligne si votre fichier est gestionarticle.html
    }*/

    // Cette méthode traite la soumission du formulaire
    @PostMapping("/save")
    public String saveMouvement(
            @RequestParam("nom") String nomArticle,
            @RequestParam("type") String typeOperation,
            @RequestParam("quantite") int quantite) {

        // Trouver l'article par son nom pour récupérer son ID
        Article article = articleRepository.findByNom(nomArticle)
                .orElseThrow(() -> new RuntimeException("Erreur : L'article '" + nomArticle + "' n'a pas été trouvé."));

        // Préparer les données pour le service
        MouvementStockDto mouvementDto = new MouvementStockDto();
        mouvementDto.setIdArticle(article.getIdArticle());
        mouvementDto.setTypeMouvement(typeOperation.toUpperCase());
        mouvementDto.setQuantiteModifiee(quantite);

        // Appeler le service qui contient toute la logique
        try {
            mouvementStockService.enregistrerMouvement(mouvementDto);
        } catch (RuntimeException e) {
            // rediriger vers la page avec un message d'erreur
            // return "redirect:/magasinier/articles?error=" + e.getMessage();
            System.err.println("Erreur lors de l'enregistrement du mouvement: " + e.getMessage());
        }

        // Rediriger vers la page d'articles pour voir le résultat
        return "redirect:/magasinier/articles";
    }
}