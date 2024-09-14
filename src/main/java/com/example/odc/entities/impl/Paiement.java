package com.example.odc.entities.impl;

import com.example.odc.database.IDatabase;
import com.example.odc.entities.Model;
import com.example.odc.entities.builders.PaiementBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Paiement extends Model<Paiement> {
    private int id;
    private double montant;
    private Dette dette;

    public Paiement(){
        super();
    }

    public Paiement(IDatabase database){
        super(database);
    }


    public Paiement(PaiementBuilder builder) {
        super();
        this.id = builder.getId();
        this.montant = builder.getMontant();
        this.dette = builder.getDette();
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

    @Override
    protected int getId(Paiement entity) {
        return entity.id;
    }

    @Override
    protected void setId(Paiement entity, int id) {
        entity.id = id;
    }
}