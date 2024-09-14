package com.example.odc.entities.impl;

import com.example.odc.database.IDatabase;
import com.example.odc.entities.Model;
import com.example.odc.entities.builders.ArticleDetteBuilder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ArticleDette  extends Model<ArticleDette> {
    private int id;
    private double prixVente;
    private int qteVente;
    private Dette dette;
    private Article article;

    public ArticleDette(){
        super();
    }

    public ArticleDette(IDatabase database){
        super(database);
    }

    public ArticleDette(ArticleDetteBuilder builder) {
        super();
        this.id = builder.getId();
        this.prixVente = builder.getPrixVente();
        this.qteVente = builder.getQteVente();
        this.dette = builder.getDette();
        this.article = builder.getArticle();
    }

    // Getters
    public int getId() {
        return id;
    }

    public double getPrixVente() {
        return prixVente;
    }

    public int getQteVente() {
        return qteVente;
    }

    public Dette getDette() {
        return dette;
    }

    public Article getArticle() {
        return article;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }

    public void setQteVente(int qteVente) {
        this.qteVente = qteVente;
    }

    public void setDette(Dette dette) {
        this.dette = dette;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    @Override
    protected int getId(ArticleDette entity) {
        return entity.id;
    }

    @Override
    protected void setId(ArticleDette entity, int id) {
        entity.id = id;
    }
}