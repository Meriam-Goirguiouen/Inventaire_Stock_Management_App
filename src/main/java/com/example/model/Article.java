/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author user
 */
@Entity
@Table(name = "article")
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a"),
    @NamedQuery(name = "Article.findByIdArticle", query = "SELECT a FROM Article a WHERE a.idArticle = :idArticle"),
    @NamedQuery(name = "Article.findByNom", query = "SELECT a FROM Article a WHERE a.nom = :nom"),
    @NamedQuery(name = "Article.findByCategorie", query = "SELECT a FROM Article a WHERE a.categorie = :categorie")})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_article")
    private Integer idArticle;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Lob
    @Column(name = "description")
    private String description;
    @Column(name = "categorie")
    private String categorie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticle")
    private Collection<MouvementStock> mouvementStockCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idArticle")
    private Collection<LigneCommandeFournisseur> ligneCommandeFournisseurCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "idArticle")
    private Stock stock;

    public Article() {
    }

    public Article(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public Article(Integer idArticle, String nom) {
        this.idArticle = idArticle;
        this.nom = nom;
    }

    public Integer getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Integer idArticle) {
        this.idArticle = idArticle;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public Collection<MouvementStock> getMouvementStockCollection() {
        return mouvementStockCollection;
    }

    public void setMouvementStockCollection(Collection<MouvementStock> mouvementStockCollection) {
        this.mouvementStockCollection = mouvementStockCollection;
    }

    public Collection<LigneCommandeFournisseur> getLigneCommandeFournisseurCollection() {
        return ligneCommandeFournisseurCollection;
    }

    public void setLigneCommandeFournisseurCollection(Collection<LigneCommandeFournisseur> ligneCommandeFournisseurCollection) {
        this.ligneCommandeFournisseurCollection = ligneCommandeFournisseurCollection;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArticle != null ? idArticle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.idArticle == null && other.idArticle != null) || (this.idArticle != null && !this.idArticle.equals(other.idArticle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.model.Article[ idArticle=" + idArticle + " ]";
    }
    
}
