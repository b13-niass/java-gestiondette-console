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

        try {
            ResultSet resultSet = database.executePreparedQuery(sql, surnom);

            if (resultSet.next()) {
                client = Client.builder()
                        .id(resultSet.getInt("id"))
                        .surnom(resultSet.getString("surnom"))
                        .telephone(resultSet.getString("telephone"))
                        .adresse(resultSet.getString("adresse"))
                        .user(resultSet.getObject("user", User.class)) // Adapt this based on your User object retrieval
                        .build();
            }
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

        try {
            ResultSet resultSet = database.executePreparedQuery(sql, telephone);

            if (resultSet.next()) {
                client = Client.builder()
                        .id(resultSet.getInt("id"))
                        .surnom(resultSet.getString("surnom"))
                        .telephone(resultSet.getString("telephone"))
                        .adresse(resultSet.getString("adresse"))
                        .user(resultSet.getObject("user", User.class)) // Adapt this based on your User object retrieval
                        .build();
            }
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
            try (ResultSet userResultSet = database.executePreparedQuery(userCheckSql, user.getLogin())) {
                if (userResultSet.next()) {
                    // User exists
                    userId = userResultSet.getInt("id");
                } else {
                    // User does not exist, create the user
                    String userInsertSql = "INSERT INTO users (nom, prenom, login, password, role) VALUES (?, ?, ?, ?, ?) RETURNING id";
                    userId = database.executePreparedUpdate(userInsertSql, user.getNom(), user.getPrenom(), user.getLogin(), user.getPassword(), user.getRole().name());
                }
            }

            // Update the existing client with the new user ID
            String clientUpdateSql = "UPDATE clients SET user_id = ? WHERE id = ?";
            int updatedRows = database.executePreparedUpdate(clientUpdateSql, userId, client.getId());

            if (updatedRows > 0) {
                // Return the updated client object with the new user ID
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