package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.Client;
import com.example.odc.repositories.ClientRepository;
import com.example.odc.repositories.JdbcRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Profile("jdbc")
@Repository
public class ClientRepoImplJDBC extends JdbcRepository<Client> implements ClientRepository {
    private final Database database;
    public ClientRepoImplJDBC(Database database) {
        super(database);
        this.database = database;
    }

    @Override
    public Client findBySurnom(String surnom) {
        return null;
    }

    @Override
    public Client findByTelephone(String telephone) {
        return null;
    }

    @Override
    public Client createAccount(Client client) {
        return null;
    }
}