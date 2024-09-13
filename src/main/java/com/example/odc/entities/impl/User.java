package com.example.odc.entities.impl;

import com.example.odc.database.IDatabase;
import com.example.odc.entities.Model;
import com.example.odc.entities.builders.UserBuilder;
import com.example.odc.enums.Role;

public class User extends Model<User> {
    private int id;
    private String nom;
    private String prenom;
    private String login;
    private String password;
    private Role role;

    public User(){
        super(null);
    }

    public User(IDatabase database){
        super(database);
    }

    public User(UserBuilder builder) {
        super(null);
        this.id = builder.getId();
        this.nom = builder.getNom();
        this.prenom = builder.getPrenom();
        this.login = builder.getLogin();
        this.password = builder.getPassword();
        this.role = builder.getRole();

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