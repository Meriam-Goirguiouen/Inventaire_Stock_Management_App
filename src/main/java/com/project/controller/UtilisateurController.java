package com.project.controller;

import com.project.model.Utilisateur;
import com.project.repository.UtilisateurRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UtilisateurController {

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("utilisateur", new Utilisateur());
        return "login"; // login.html
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("utilisateur") Utilisateur utilisateurForm,
                        HttpSession session,
                        Model model) {

        Utilisateur utilisateur = utilisateurRepository.findByEmail(utilisateurForm.getEmail());

        if (utilisateur != null && utilisateur.getMotDePasse().equals(utilisateurForm.getMotDePasse())) {
            session.setAttribute("utilisateur", utilisateur);

            // Redirect by role
            switch (utilisateur.getRole()) {
                case FOURNISSEUR:
                    return "redirect:/supplier/dashboard";
                case ADMINISTRATEUR:
                    return "redirect:/admin/accueil";
                case MAGASINIER:
                    return "redirect:/magasinier";
                default:
                    model.addAttribute("error", "Rôle inconnu.");
                    return "login";
            }
        }

        model.addAttribute("error", "Email ou mot de passe incorrect.");
        return "login";
    }

    @GetMapping("/")
    public String homeRedirect() {
        return "redirect:/login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
