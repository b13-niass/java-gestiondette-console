package com.example.odc.entities.builders;

import com.example.odc.entities.impl.Dette;
import com.example.odc.entities.impl.Paiement;

public class PaiementBuilder {
    private int id;
    private double montant;
    private Dette dette;

    public PaiementBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public int getId() {
        return id;
    }

    public double getMontant() {
        return montant;
    }

    public Dette getDette() {
        return dette;
    }

    public PaiementBuilder setMontant(double montant) {
        this.montant = montant;
        return this;
    }

    public PaiementBuilder setDette(Dette dette) {
        this.dette = dette;
        return this;
    }

    public Paiement build() {
        return new Paiement(this);
    }
}
