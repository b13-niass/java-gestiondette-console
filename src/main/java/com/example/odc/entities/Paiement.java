package com.example.odc.entities;

import com.example.odc.database.Database;

public class Paiement {
    private int id;
    private double montant;
    private Dette dette;

    public Paiement(){
        super();
    }

    public Paiement(Database database){
    }

    // Getters
    public int getId() {
        return id;
    }

    public double getMontant() {
        return montant;
    }

    public Dette getDette() {
        return dette;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setDette(Dette dette) {
        this.dette = dette;
    }
}