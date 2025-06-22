package com.project.controller;

import com.project.dto.DeliveryInfoDto;
import com.project.dto.DocumentDto;
import com.project.dto.OrderActionDto;
import com.project.model.CommandeFournisseur;
import com.project.util.PdfGenerator;
import java.io.ByteArrayInputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Calendar;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("/supplier")
public class SupplierDashboardController {

    private Date daysAgo(int days) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -days);
        return cal.getTime();
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("supplierName", "Fournisseur Démo");
        model.addAttribute("message", "Bienvenue sur votre tableau de bord de démonstration !");
        model.addAttribute("pendingOrdersCount", 3);
        model.addAttribute("newNotificationsCount", 1);
        return "supplier/SupplierDashboard";
    }

    @GetMapping("/orders/view")
    public String viewOrders(Model model) {
        List<CommandeFournisseur> orders = Arrays.asList(
            new CommandeFournisseur(101, daysAgo(3), "En attente"),
            new CommandeFournisseur(102, daysAgo(5), "Confirmée"),
            new CommandeFournisseur(103, daysAgo(10), "Livrée")
        );
        model.addAttribute("orders", orders);
        return "supplier/orders/ViewOrders";
    }

    @GetMapping("/orders/confirm-or-decline")
    public String confirmOrDeclineForm(@RequestParam Integer orderId, Model model) {
        model.addAttribute("order", new CommandeFournisseur(orderId, daysAgo(3), "En attente"));
        model.addAttribute("orderAction", new OrderActionDto(orderId.longValue(), ""));
        return "supplier/orders/ConfirmDeclineOrder";
    }

    @PostMapping("/orders/confirm-or-decline")
    public String processConfirmOrDecline(@ModelAttribute OrderActionDto orderAction) {
        return "redirect:/supplier/orders/view";
    }

//    @GetMapping("/orders/add-comments")
//    public String addCommentsForm(@RequestParam Integer orderId, Model model) {
//        model.addAttribute("orderId", orderId);
//        model.addAttribute("deliveryInfo", new DeliveryInfoDto(orderId.longValue(), ""));
//        return "supplier/AddDeliveryInfo";
//    }

    @PostMapping("/orders/add-comments")
    public String processAddComments(@ModelAttribute DeliveryInfoDto deliveryInfo) {
        return "redirect:/supplier/orders/view";
    }

    @GetMapping("/orders/track")
    public String trackOrders(Model model) {
        List<CommandeFournisseur> deliveredOrders = Arrays.asList(
            new CommandeFournisseur(100, daysAgo(20), "Livrée"),
            new CommandeFournisseur(99, daysAgo(30), "Livrée")
        );
        model.addAttribute("deliveredOrders", deliveredOrders);
        return "supplier/orders/TrackOrders";
    }

    @GetMapping("/documents/download")
    public String listDocuments(Model model) {
        List<DocumentDto> documents = Arrays.asList(
            new DocumentDto(1L, "bon_commande_101.pdf", "application/pdf", null, 101L),
            new DocumentDto(2L, "facture_101.pdf", "application/pdf", null, 101L)
        );
        model.addAttribute("documents", documents);
        return "supplier/documents/DownloadDocs";
    }

    @GetMapping("/documents/download/{id}")
    public ResponseEntity<InputStreamResource> downloadOrderPdf(@PathVariable Integer id) {
    // Example: fetch fake order
    CommandeFournisseur order = new CommandeFournisseur(id, new Date(), "Confirmée");

    ByteArrayInputStream bis = PdfGenerator.generateOrderPdf("Fournisseur Démo", order);

    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Disposition", "inline; filename=bon_commande_" + id + ".pdf");

    return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
}

    
}
