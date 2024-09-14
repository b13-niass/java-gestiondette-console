package com.example.odc.entities.builders;

import com.example.odc.entities.impl.Client;
import com.example.odc.entities.impl.Dette;

public class DetteBuilder {
    private int id;
    private double montant;
    private Client client;

    public DetteBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public double getMontant() {
        return montant;
    }

    public Client getClient() {
        return client;
    }

    public DetteBuilder setMontant(double montant) {
        this.montant = montant;
        return this;
    }

    public DetteBuilder setClient(Client client) {
        this.client = client;
        return this;
    }

    public Dette build() {
        return new Dette(this);
    }
}
