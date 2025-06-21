package com.example.controller;

import com.example.model.CommandeFournisseur;
import com.example.model.Document;
import com.example.service.CommandeFournisseurService;
import com.example.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierDashboardController {

    @Autowired
    private CommandeFournisseurService commandeService;

    @Autowired
    private DocumentService documentService;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("supplierName", "Fournisseur connecté");
        model.addAttribute("pendingOrdersCount", commandeService.countByStatut("En attente"));
        model.addAttribute("newNotificationsCount", 2); // Change if you use a NotificationService
        return "supplier/SupplierDashboard";
    }

    @GetMapping("/orders/view")
    public String viewOrders(Model model) {
        List<CommandeFournisseur> orders = commandeService.findAll();
        model.addAttribute("orders", orders);
        return "supplier/orders/ViewOrders";
    }

    @GetMapping("/orders/confirm-or-decline")
    public String confirmOrDeclineForm(@RequestParam("orderId") Integer orderId, Model model) {
        CommandeFournisseur order = commandeService.findById(orderId);
        model.addAttribute("order", order);
        return "supplier/orders/ConfirmDeclineOrder";
    }

    @PostMapping("/orders/confirm-or-decline")
    public String processConfirmation(@RequestParam Integer orderId, @RequestParam String action) {
        commandeService.updateStatut(orderId, action.equals("confirm") ? "Confirmée" : "Refusée");
        return "redirect:/supplier/orders/view";
    }

    @GetMapping("/orders/add-comments")
    public String addCommentsForm(@RequestParam("orderId") Integer orderId, Model model) {
        model.addAttribute("orderId", orderId);
        return "supplier/orders/AddDeliveryInfo";
    }

    @PostMapping("/orders/add-comments")
    public String submitDeliveryInfo(@RequestParam("orderId") Integer orderId, @RequestParam("comment") String comment) {
        commandeService.addDeliveryComment(orderId, comment);
        return "redirect:/supplier/orders/view";
    }

    @GetMapping("/orders/track")
    public String trackOrders(Model model) {
        List<CommandeFournisseur> deliveredOrders = commandeService.findByStatut("Livrée");
        model.addAttribute("deliveredOrders", deliveredOrders);
        return "supplier/orders/TrackOrders";
    }

    @GetMapping("/documents/download")
    public String showDocuments(Model model) {
        List<Document> docs = documentService.findAll();
        model.addAttribute("documents", docs);
        return "supplier/documents/DownloadDocs";
    }

    @GetMapping("/documents/download/{id}")
    public String downloadDocument(@PathVariable("id") Long id) {
        // TODO: implement actual file streaming logic with MediaType.APPLICATION_PDF
        return "redirect:/supplier/documents/download";
    }
}
