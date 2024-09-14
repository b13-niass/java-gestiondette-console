package com.example.odc.entities.builders;

import com.example.odc.entities.impl.Client;
import com.example.odc.entities.impl.User;

public class ClientBuilder {
    private int id;
    private String surnom;
    private String telephone;
    private String adresse;
    private User user;

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

    public ClientBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public ClientBuilder setSurnom(String surnom) {
        this.surnom = surnom;
        return this;
    }

    public ClientBuilder setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public ClientBuilder setAdresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public ClientBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public Client build() {
        return new Client(this);
    }
}