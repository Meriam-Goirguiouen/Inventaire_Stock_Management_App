package com.project.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;

@Entity
public class Alerte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private LocalDateTime dateSend;

    @ManyToOne
    @JoinColumn(name = "produit_id")
    private Produit produit;

    // Getters & Setters
    public Long getId() {
        return this.id;
    }

    public String getMessage() {
        return this.message;
    }

    public LocalDateTime getDateSend() {
        return this.dateSend;
    }

    public Produit getProduit() {
        return this.produit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateSend(LocalDateTime dateSend) {
        this.dateSend = dateSend;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
