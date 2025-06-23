package com.project.controller;

import com.project.model.Utilisateur;
import com.project.service.BonCommandeService;
import com.project.service.UtilisateurService;
import com.project.repository.UtilisateurRepository;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UtilisateurService utilisateurService;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private BonCommandeService bonCommandeService;

    @GetMapping("/accueil")
    public String showAdminDashboard(Model model, HttpSession session) {
        Utilisateur admin = (Utilisateur) session.getAttribute("utilisateur");
        if (admin == null || admin.getRole() != Utilisateur.Role.ADMINISTRATEUR) {
            return "redirect:/login";
        }
        long fournisseurCount = utilisateurService.countFournisseurs(); // custom method to count only suppliers
        long commandeCount = bonCommandeService.countCommandes();

        model.addAttribute("fournisseurCount", fournisseurCount);
        model.addAttribute("commandeCount", commandeCount);
        model.addAttribute("message", "Voici un aperçu de votre tableau de bord.");

        return "admin/accueil"; // path to your Thymeleaf template
    }
    
    @GetMapping("/utilisateurs")
    public String listeUtilisateurs(Model model) {
        List<Utilisateur> utilisateurs = utilisateurRepository.findAll();
        model.addAttribute("utilisateurs", utilisateurs);
        return "/admin/utilisateurs"; // le nom du fichier HTML
    }
    
    @GetMapping("/utilisateurs/edit/{id}")
    public String showEditForm(@PathVariable Integer id, Model model) {
        Utilisateur utilisateur = utilisateurService.getUtilisateurById(id);
        if (utilisateur == null) {
            // Rediriger vers la liste si l'utilisateur n'existe pas
            return "redirect:/admin/utilisateurs";
        }
        model.addAttribute("utilisateur", utilisateur);
        return "admin/edit-utilisateur"; // Assure-toi que ce fichier existe dans templates/admin/
    }

    @PostMapping("/utilisateurs/edit")
    public String modifierUtilisateur(@ModelAttribute Utilisateur utilisateur) {
        if (utilisateur.getId() == null) {
            // Si l'id est null, on ne peut pas modifier un utilisateur existant
            // Soit tu rediriges, soit tu lances une erreur
            return "redirect:/admin/utilisateurs";
        }

        Optional<Utilisateur> existing = utilisateurRepository.findById(utilisateur.getId());

        if (existing.isPresent()) {
            Utilisateur ancien = existing.get();

            // Conserver le mot de passe existant si le nouveau est vide
            if (utilisateur.getMotDePasse() == null || utilisateur.getMotDePasse().isBlank()) {
                utilisateur.setMotDePasse(ancien.getMotDePasse());
            } else {
                // Si tu utilises un encodage (recommandé), encode ici le nouveau mot de passe
                // utilisateur.setMotDePasse(passwordEncoder.encode(utilisateur.getMotDePasse()));
            }

            // IMPORTANT : pour éviter un insert, s’assurer que l’ID est bien présent
            utilisateur.setId(ancien.getId());
        } else {
            // L'utilisateur n'existe pas, on ne fait rien ou on redirige
            return "redirect:/admin/utilisateurs";
        }

        utilisateurRepository.save(utilisateur);

        return "redirect:/admin/utilisateurs";
    }
    
    @GetMapping("/utilisateurs/add")
    public String showAddForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "admin/add-utilisateur";  // Le fichier add-utilisateur.html
    }

    // Traite l'ajout de l'utilisateur depuis le formulaire
    @PostMapping("/utilisateurs/add")
    public String addUtilisateur(@ModelAttribute Utilisateur utilisateur) {
        // Tu peux ajouter une vérification ou hashage du mot de passe ici
        utilisateurRepository.save(utilisateur);
        return "redirect:/admin/utilisateurs";
    }
    
    // Suppression d'un utilisateur par son ID
    @GetMapping("/utilisateurs/delete/{id}")
    public String deleteUtilisateur(@PathVariable Long id) {
        utilisateurRepository.deleteById(id);
        return "redirect:/admin/utilisateurs";  // Redirige vers la liste après suppression
    }

    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidate the current session, logging the user out
        return "redirect:/login";  // Redirect to the login page
    }



}
