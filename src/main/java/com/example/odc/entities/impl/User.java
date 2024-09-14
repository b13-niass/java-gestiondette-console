package com.example.odc.entities.impl;

import com.example.odc.database.IDatabase;
import com.example.odc.entities.Model;
import com.example.odc.entities.builders.UserBuilder;
import com.example.odc.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User extends Model<User> {
    private int id;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private Role role;

    public User(){
        super();
    }

    public User(IDatabase database){
        super(database);
    }

    public User(UserBuilder builder) {
        super();
        this.id = builder.getId();
        this.nom = builder.getNom();
        this.prenom = builder.getPrenom();
        this.login = builder.getLogin();
        this.password = builder.getPassword();
        this.role = builder.getRole();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    protected int getId(User entity) {
        return entity.id;
    }

    @Override
    protected void setId(User entity, int id) {
        entity.id = id;
    }
}