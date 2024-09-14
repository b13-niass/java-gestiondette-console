package com.example.odc.entities.builders;

import com.example.odc.entities.impl.Article;

public class ArticleBuilder {
    private int id;
    private String libelle;
    private double prixUnitaire;
    private int quantiteEnStock;

    public ArticleBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public ArticleBuilder setLibelle(String libelle) {
        this.libelle = libelle;
        return this;
    }

    public ArticleBuilder setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
        return this;
    }

    public ArticleBuilder setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
        return this;
    }

    public Article build() {
        return new Article(this);
    }
}
