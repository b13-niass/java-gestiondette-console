package com.example.odc.entities;

import com.example.odc.database.Database;

public class ArticleDette{
    private int id;
    private double prixVente;
    private int qteVente;
    private Dette dette;
    private Article article;

    public ArticleDette(){
        super();
    }

    public ArticleDette(Database database){
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

}