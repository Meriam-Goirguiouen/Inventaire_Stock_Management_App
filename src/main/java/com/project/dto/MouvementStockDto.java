package com.project.dto;

// Un DTO simple pour recevoir les données du formulaire
public class MouvementStockDto {

    private Integer idArticle;
    private String typeMouvement; // "ENTREE" ou "SORTIE"
    private int quantiteModifiee;

    // Getters et Setters
    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public int getQuantiteModifiee() {
        return quantiteModifiee;
    }

    public void setQuantiteModifiee(int quantiteModifiee) {
        this.quantiteModifiee = quantiteModifiee;
    }
}