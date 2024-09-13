package com.example.odc.entities.impl;

import com.example.odc.entities.Model;

public class Client extends Model<Client> {
    private int id;
    private String surnom;
    private String telephone;
    private String adresse;
    private User user = null;

    @Override
    protected int getId(Client entity) {
        return entity.id;
    }

    @Override
    protected void setId(Client entity, int id) {
        entity.id = id;
    }
}