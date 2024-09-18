package com.example.odc.entities;

import com.example.odc.database.Database;

import java.util.List;

public class Dette {
    private int id;
    private double montant;
    private Client client;

    public Dette(){
        super();
    }

    public Dette(Database database){
    }

    // Getters
    public int getId() {
        return id;
    }

    public double getMontant() {
        return montant;
    }

    public Client getClient() {
        return client;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ArticleDette> getArticlesDette(int idD) {
        return null;
    }
}