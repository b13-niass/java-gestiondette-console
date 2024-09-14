package com.example.odc.entities.impl;

import java.util.List;

public class JsonDataModel {
    private List<Article> articles;
    private List<Dette> dettes;
    private List<Client> clients;
    private List<Paiement> paiements;
    private List<User> users;
    private List<ArticleDette> articleDettes;

    // Getters et Setters
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Dette> getDettes() {
        return dettes;
    }

    public void setDettes(List<Dette> dettes) {
        this.dettes = dettes;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<ArticleDette> getArticleDettes() {
        return articleDettes;
    }

    public void setArticleDettes(List<ArticleDette> articleDettes) {
        this.articleDettes = articleDettes;
    }

    @Override
    public String toString() {
        return "JsonDataModel{" +
                "articles=" + articles +
                ", dettes=" + dettes +
                ", clients=" + clients +
                ", paiements=" + paiements +
                ", users=" + users +
                ", articleDettes=" + articleDettes +
                '}';
    }
}