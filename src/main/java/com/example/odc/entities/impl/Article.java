package com.example.odc.entities.impl;

import com.example.odc.database.IDatabase;
import com.example.odc.entities.Model;
import com.example.odc.entities.builders.ArticleBuilder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Article extends Model<Article> {
    private int id;
    private String libelle;
    private double prixUnitaire;
    private int quantiteEnStock;

    public Article(){
        super();
    }

    public Article(IDatabase database){
        super(database);
    }

    public Article(ArticleBuilder builder) {
        super();
        this.id = builder.getId();
        this.libelle = builder.getLibelle();
        this.prixUnitaire = builder.getPrixUnitaire();
        this.quantiteEnStock = builder.getQuantiteEnStock();
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    @Override
    protected int getId(Article entity) {
        return entity.id;
    }

    @Override
    protected void setId(Article entity, int id) {
        entity.id = id;
    }

}