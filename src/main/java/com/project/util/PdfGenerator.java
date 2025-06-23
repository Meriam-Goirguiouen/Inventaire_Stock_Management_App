package com.project.util;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.project.model.BonCommande;
import java.awt.Color;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class PdfGenerator {

    public static ByteArrayInputStream generateOrderPdf(String nomFournisseur, BonCommande commande) {
        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            // Titre
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("Bon de Commande", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));
            
            // Informations du fournisseur et commande
            Font boldFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);
            document.add(new Paragraph("Fournisseur : " + nomFournisseur, boldFont));
            document.add(new Paragraph("Date : " + 
                (commande.getDateCreation() != null
                    ? commande.getDateCreation().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                    : "N/A")));
            document.add(new Paragraph("État de la commande : " + commande.getEtat()));
            document.add(new Paragraph(" "));

            // Commentaire (si présent)
//            if (commande.getCommentaire() != null && !commande.getCommentaire().isEmpty()) {
//                document.add(new Paragraph("Commentaire : " + commande.getCommentaire()));
//                document.add(new Paragraph(" "));
//            }

            // Table des lignes de commande (à adapter à ton modèle)
            if (commande.getLignes() != null && !commande.getLignes().isEmpty()) {
                PdfPTable table = new PdfPTable(3); // Produit, Quantité, Prix
                table.setWidthPercentage(100);
                table.setWidths(new float[]{4f, 2f, 2f});

                // En-têtes
                Stream.of("Produit", "Quantité", "Prix (MAD)").forEach(headerTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(Color.LIGHT_GRAY);
                    header.setPhrase(new Phrase(headerTitle));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(header);
                });

//                // Contenu
//                for (var ligne : commande.getLignes()) {
//                    table.addCell(new Phrase(ligne.getLignes()));
//                    table.addCell(String.valueOf(ligne.getQuantite()));
//                    table.addCell(String.format("%.2f", ligne.getPrix()));
//                }
//
//                document.add(table);
            } else {
                document.add(new Paragraph("Aucune ligne de commande."));
            }

            document.close();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
