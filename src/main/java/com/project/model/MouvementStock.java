package com.project.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class MouvementStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMouvement;

    private int idArticle;

    private Integer idMagasinier;

    private LocalDateTime dateMouvement;

    private String typeMouvement;

    private int quantiteModifiee;

    // Getters et Setters
    public int getIdMouvement() {
        return idMouvement;
    }

    public void setIdMouvement(int idMouvement) {
        this.idMouvement = idMouvement;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public Integer getIdMagasinier() {
        return idMagasinier;
    }

    public void setIdMagasinier(Integer idMagasinier) {
        this.idMagasinier = idMagasinier;
    }

    public LocalDateTime getDateMouvement() {
        return dateMouvement;
    }

    public void setDateMouvement(LocalDateTime dateMouvement) {
        this.dateMouvement = dateMouvement;
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
