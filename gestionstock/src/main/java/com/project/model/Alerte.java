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
    private Produit produit;

    // Getters & Setters 
    public Long getId(){
        return this.id;
    }
    public String getMessage(){
        return this.message;
    }
    public LocalDateTime getDateSend(){
        return this.dateSend;
    }
    public void setMessage(String msg){
        this.message = msg;
    }
    public void setDateSend(LocalDateTime dateS){
        this.dateSend = dateS;
    }
    
}
