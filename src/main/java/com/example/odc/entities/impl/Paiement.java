package com.example.odc.entities.impl;

import com.example.odc.entities.Model;

public class Paiement extends Model<Paiement> {
    private int id;
    private double montant;
    private Dette dette;

    @Override
    protected int getId(Paiement entity) {
        return entity.id;
    }

    @Override
    protected void setId(Paiement entity, int id) {
        entity.id = id;
    }
}