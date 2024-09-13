package com.example.odc.entities.impl;

import com.example.odc.entities.Model;

public class ArticleDette  extends Model<ArticleDette> {
    private int id;
    private double prixVente;
    private int qteVente;
    private Dette dette;

    @Override
    protected int getId(ArticleDette entity) {
        return entity.id;
    }

    @Override
    protected void setId(ArticleDette entity, int id) {
        entity.id = id;
    }
}