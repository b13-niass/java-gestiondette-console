package com.example.odc.entities.impl;

import com.example.odc.entities.Model;

public class Dette extends Model<Dette> {
    private int id;
    private double montant;
    private Client client;

    @Override
    protected int getId(Dette entity) {
        return entity.id;
    }

    @Override
    protected void setId(Dette entity, int id) {
        entity.id = id;
    }
}