package com.example.odc.entities.impl;

import com.example.odc.database.IDatabase;
import com.example.odc.entities.Model;
import com.example.odc.entities.builders.DetteBuilder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Dette extends Model<Dette> {
    private int id;
    private double montant;
    private Client client;

    public Dette(){
        super();
    }

    public Dette(IDatabase database){
        super(database);
    }

    public Dette(DetteBuilder builder) {
        super();
        this.id = builder.getId();
        this.montant = builder.getMontant();
        this.client = builder.getClient();
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

    @Override
    protected int getId(Dette entity) {
        return entity.id;
    }

    @Override
    protected void setId(Dette entity, int id) {
        entity.id = id;
    }
}