package com.project.controller;

import com.project.model.BonCommande;
import com.project.model.Utilisateur;
import com.project.service.BonCommandeService;
import com.project.util.PdfGenerator;

import jakarta.servlet.http.HttpSession;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/supplier")
public class SupplierDashboardController {

    @Autowired
    private BonCommandeService bonCommandeService;

    // Tableau de bord du fournisseur
    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session) {
        Utilisateur fournisseur = (Utilisateur) session.getAttribute("utilisateur");
        if (fournisseur == null || fournisseur.getRole() != Utilisateur.Role.FOURNISSEUR) {
            return "redirect:/login";
        }

        List<BonCommande> commandes = bonCommandeService.getBonCommandesByFournisseur(fournisseur);

        model.addAttribute("supplierName", fournisseur.getNom());
        model.addAttribute("pendingOrdersCount", commandes.stream()
            .filter(c -> c.getEtat() == BonCommande.EtatCommande.EN_ATTENTE)
            .count());
        model.addAttribute("newNotificationsCount", 1); // À adapter
        model.addAttribute("message", "Bienvenue, " + fournisseur.getNom() + " !");
        return "supplier/SupplierDashboard";
    }

    // Liste des commandes du fournisseur
    @GetMapping("/orders/view")
    public String viewOrders(Model model, HttpSession session) {
        Utilisateur fournisseur = (Utilisateur) session.getAttribute("utilisateur");
        if (fournisseur == null) return "redirect:/login";

        List<BonCommande> commandes = bonCommandeService.getBonCommandesByFournisseur(fournisseur);
        model.addAttribute("orders", commandes);
        return "supplier/orders/ViewOrders";
    }

    // Formulaire de confirmation/refus
    @GetMapping("/orders/confirm-or-decline")
    public String confirmOrDeclineForm(@RequestParam Long orderId, Model model, HttpSession session) {
        Utilisateur fournisseur = (Utilisateur) session.getAttribute("utilisateur");
        if (fournisseur == null) return "redirect:/login";

        BonCommande commande = bonCommandeService.getBonCommandeById(orderId);
        if (commande == null || !commande.getFournisseur().getId().equals(fournisseur.getId())) {
            return "redirect:/supplier/orders/view";
        }

        model.addAttribute("order", commande);  // This makes "order" available for binding if you want
        return "supplier/orders/ConfirmDeclineOrder";
    }


    // Traitement confirmation/refus
    @PostMapping("/orders/confirm-or-decline")
    public String confirmOrDeclineOrder(@RequestParam Long id, @RequestParam String etat, HttpSession session) {
        Utilisateur fournisseur = (Utilisateur) session.getAttribute("utilisateur");
        if (fournisseur == null) return "redirect:/login";

        BonCommande existingOrder = bonCommandeService.getBonCommandeById(id);

        if (existingOrder != null && existingOrder.getFournisseur().getId().equals(fournisseur.getId())) {
            // Validate and update status
            try {
                BonCommande.EtatCommande nouvelEtat = BonCommande.EtatCommande.valueOf(etat);
                existingOrder.setEtat(nouvelEtat);
                bonCommandeService.createBonCommande(existingOrder); // use create or save method
            } catch (IllegalArgumentException e) {
                // invalid enum value
                // handle error if needed
            }
        }

        return "redirect:/supplier/orders/view";
    }



//     Ajout de commentaire (exemple d'utilisation)
    @PostMapping("/orders/add-comments")
    public String processAddComments(@RequestParam Long orderId, @RequestParam String comment, HttpSession session) {
//        Utilisateur fournisseur = (Utilisateur) session.getAttribute("utilisateur");
//        if (fournisseur == null) return "redirect:/login";
//
//        BonCommande commande = bonCommandeService.getBonCommandeById(orderId);
//        if (commande != null && commande.getFournisseur().getId().equals(fournisseur.getId())) {
//            commande.setCommentaire(comment); // suppose que le champ existe
//            bonCommandeService.createBonCommande(commande); // save updated
//        }

        return "redirect:/supplier/orders/view";
    }
    
    @GetMapping("/orders/track")
    public String trackOrders(Model model, HttpSession session) {
        // Get the logged-in supplier from session
        Utilisateur fournisseur = (Utilisateur) session.getAttribute("utilisateur");
        if (fournisseur == null) {
            return "redirect:/login"; // if not logged in, redirect to login page
        }

        // Get all orders for this supplier
        List<BonCommande> livreeCommandes = bonCommandeService.getLivreeOrderByFournisseur(fournisseur);

        // Add orders to the model to be displayed in the Thymeleaf template
        model.addAttribute("orders", livreeCommandes);

        // Return the Thymeleaf template name (track.html in templates/supplier/orders/)
        return "supplier/orders/TrackOrders";
    } 
    
    @GetMapping("/documents/download")
    public String showOrders(Model model, HttpSession session) {
        Utilisateur fournisseur = (Utilisateur) session.getAttribute("utilisateur");
        if (fournisseur == null) return "redirect:/login";

        List<BonCommande> commandes = bonCommandeService.getBonCommandesByFournisseur(fournisseur);
        model.addAttribute("commandes", commandes);

        return "supplier/documents/DownloadDocs";  // Thymeleaf page listing orders
    }

    // Télécharger le PDF de la commande
    @GetMapping("/documents/download/{id}")
    public ResponseEntity<InputStreamResource> downloadOrderPdf(@PathVariable Long id, HttpSession session) {
        Utilisateur fournisseur = (Utilisateur) session.getAttribute("utilisateur");
        if (fournisseur == null) return ResponseEntity.status(401).build();

        BonCommande commande = bonCommandeService.getBonCommandeById(id);
        if (commande == null || !commande.getFournisseur().getId().equals(fournisseur.getId())) {
            return ResponseEntity.status(403).build();
        }

        // Generate PDF dynamically (not stored in DB)
        ByteArrayInputStream bis = PdfGenerator.generateOrderPdf(fournisseur.getNom(), commande);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=bon_commande_" + id + ".pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();  // Invalidate the current session, logging the user out
        return "redirect:/login";  // Redirect to the login page
    }

}
