package com.example.odc.entities;

import com.example.odc.database.Database;

public class Client{
    private int id;
    private String surnom;
    private String telephone;
    private String adresse;
    private User user = null;

    public Client(){
        super();
    }

    public Client(Database database){
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

}