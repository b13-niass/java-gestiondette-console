package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.Client;
import com.example.odc.entities.User;
import com.example.odc.repositories.ClientIRepository;
import com.example.odc.repositories.JdbcIRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ClientRepoImplJDBC extends JdbcIRepository<Client> implements ClientIRepository {
    private final Database database;
    public ClientRepoImplJDBC(Database database) {
        super(database);
        resolveEntityType();
        this.database = database;
    }

    @Override
    public Client findBySurnom(String surnom) {
        Client client = null;
        String sql = "SELECT * FROM clients WHERE surnom = ?";
        Client c = Client.builder().surnom(surnom).build();
        try {
           client = this.database.executePreparedQuery(sql, Client.class, c);
        } catch (SQLException e) {
            System.err.println("Error finding client by surnom: " + e.getMessage());
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client findByTelephone(String telephone) {
        Client client = null;
        String sql = "SELECT * FROM clients WHERE telephone = ?";
        Client c = Client.builder().telephone(telephone).build();
        try {
            client = database.executePreparedQuery(sql, Client.class ,c);
        } catch (SQLException e) {
            System.err.println("Error finding client by telephone: " + e.getMessage());
            e.printStackTrace();
        }
        return client;
    }

    @Override
    public Client createAccount(Client client) {
        User user = client.getUser();
        if (user == null) {
            throw new IllegalArgumentException("Client must have an associated User.");
        }

        String userCheckSql = "SELECT id FROM users WHERE login = ?";
        Integer userId = null;

        try {
            // Check if the user already exists
            User user1 = database.executePreparedQuery(userCheckSql,User.class, user.getLogin());
                if (user1 != null) {
                    // User exists
                    userId = user1.getId();
                } else {
                    String userInsertSql = "INSERT INTO users (nom, prenom, login, password, role) VALUES (?, ?, ?, ?, ?) RETURNING id";
                    userId = database.executePreparedUpdate(userInsertSql, user.getNom(), user.getPrenom(), user.getLogin(), user.getPassword(), user.getRole().name());
                }


            String clientUpdateSql = "UPDATE clients SET user_id = ? WHERE id = ?";
            int updatedRows = database.executePreparedUpdate(clientUpdateSql, userId, client.getId());

            if (updatedRows > 0) {
                return client.toBuilder().user(user).build();
            } else {
                throw new SQLException("Failed to update client.");
            }
        } catch (SQLException e) {
            System.err.println("Error processing user or client: " + e.getMessage());
            e.printStackTrace();
            return null; // Handle the error as appropriate
        }
    }

}