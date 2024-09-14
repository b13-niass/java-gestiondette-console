package com.example.odc.entities.impl;

import com.example.odc.database.IDatabase;
import com.example.odc.entities.Model;
import com.example.odc.entities.builders.ClientBuilder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Client extends Model<Client> {
    private int id;
    private String surnom;
    private String telephone;
    private String adresse;
    private User user = null;

    public Client(){
        super();
    }

    public Client(IDatabase database){
        super(database);
    }

    public Client(ClientBuilder builder) {
        super();
        this.id = builder.getId();
        this.surnom = builder.getSurnom();
        this.telephone = builder.getTelephone();
        this.adresse = builder.getAdresse();
        this.user = builder.getUser();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getSurnom() {
        return surnom;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public User getUser() {
        return user;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setSurnom(String surnom) {
        this.surnom = surnom;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    protected int getId(Client entity) {
        return entity.id;
    }

    @Override
    protected void setId(Client entity, int id) {
        entity.id = id;
    }
}