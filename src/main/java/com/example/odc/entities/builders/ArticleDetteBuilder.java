package com.example.odc.entities.builders;

import com.example.odc.entities.impl.Article;
import com.example.odc.entities.impl.ArticleDette;
import com.example.odc.entities.impl.Dette;

public class ArticleDetteBuilder {
    private int id;
    private double prixVente;
    private int qteVente;
    private Dette dette;
    private Article article;

    public ArticleDetteBuilder setId(int id) {
        this.id = id;
        return this;
    }

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

    public ArticleDetteBuilder setPrixVente(double prixVente) {
        this.prixVente = prixVente;
        return this;
    }

    public ArticleDetteBuilder setQteVente(int qteVente) {
        this.qteVente = qteVente;
        return this;
    }

    public ArticleDetteBuilder setDette(Dette dette) {
        this.dette = dette;
        return this;
    }
    public ArticleDetteBuilder setArticle(Article article) {
        this.article = article;
        return this;
    }

    public ArticleDette build() {
        return new ArticleDette(this);
    }
}
