// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
//  */
 package com.project.util;

// import com.project.model.CommandeFournisseur;
// import com.lowagie.text.*;
// import com.lowagie.text.pdf.PdfWriter;

// import java.io.ByteArrayInputStream;
// import java.io.ByteArrayOutputStream;
// import java.util.Date;

 public class PdfGenerator {

//     public static ByteArrayInputStream generateOrderPdf(String supplierName, CommandeFournisseur order) {
//         Document document = new Document();
//         ByteArrayOutputStream out = new ByteArrayOutputStream();

//         try {
//             PdfWriter.getInstance(document, out);
//             document.open();

//             // Title
//             Font titleFont = new Font(Font.HELVETICA, 20, Font.BOLD);
//             Paragraph title = new Paragraph("Bon de commande", titleFont);
//             title.setAlignment(Element.ALIGN_CENTER);
//             document.add(title);
//             document.add(Chunk.NEWLINE);

//             // Order Info
//             document.add(new Paragraph("Fournisseur: " + supplierName));
//             document.add(new Paragraph("ID commande: " + order.getIdCommande()));
//             document.add(new Paragraph("Date commande: " + order.getDateCommande()));
//             document.add(new Paragraph("Statut: " + order.getStatut()));
//             document.add(new Paragraph("Date de génération: " + new Date()));
//             document.add(Chunk.NEWLINE);

//             // You can add order lines here if you have them
//             // document.add(new Paragraph("Articles..."));

//             document.close();
//         } catch (Exception e) {
//             e.printStackTrace();
//         }

//         return new ByteArrayInputStream(out.toByteArray());
//     }
}

