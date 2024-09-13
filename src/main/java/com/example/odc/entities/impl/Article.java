package com.example.odc.entities.impl;

import com.example.odc.entities.Model;

public class Article extends Model<Article> {
    private int id;
    private String libelle;
    private double prixUnitaire;
    private int quantiteEnStock;

    @Override
    protected int getId(Article entity) {
        return entity.id;
    }

    @Override
    protected void setId(Article entity, int id) {
        entity.id = id;
    }
}