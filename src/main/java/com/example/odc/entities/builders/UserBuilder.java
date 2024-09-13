package com.example.odc.entities.builders;

import com.example.odc.entities.impl.User;
import com.example.odc.enums.Role;

public class UserBuilder {
    private int id;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private Role role;

    public UserBuilder setId(int id) {
        this.id = id;
        return this;
    }

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

    public UserBuilder setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public UserBuilder setPrenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public UserBuilder setLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setRole(Role role) {
        this.role = role;
        return this;
    }

    public User build() {
        return new User(this);
    }
}
