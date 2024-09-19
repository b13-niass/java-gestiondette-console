package com.example.odc.database.impl;

import com.example.odc.database.Database;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DatabaseFactory implements Database {
    @Value("${database.url}")
    private String URL;
    @Value("${database.username}")
    private String USER;
    @Value("${database.password}")
    private String PASSWORD;
    private Connection connection;

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Database connection established.");
            }
        } catch (SQLException e) {
            System.err.println("Error establishing database connection: " + e.getMessage());
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public boolean closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
                return true;
            }
        } catch (SQLException e) {
            System.err.println("Error closing database connection: " + e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ResultSet executeQuery(String query) {
        ResultSet resultSet = null;
        try {
            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            resultSet = statement.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Error executing query: " + e.getMessage());
            e.printStackTrace();
        }
        return resultSet;
    }

    @Override
    public int executeUpdate(String query) {
        int affectedRows = 0;
        try {
            Connection conn = getConnection();
            Statement statement = conn.createStatement();
            affectedRows = statement.executeUpdate(query);
        } catch (SQLException e) {
            System.err.println("Error executing update: " + e.getMessage());
            e.printStackTrace();
        }
        return affectedRows;
    }

    // Method to execute a prepared query
    public ResultSet executePreparedQuery(String sql, Object... params) {
        ResultSet resultSet = null;
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            // Set parameters
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.err.println("Error executing prepared query: " + e.getMessage());
            e.printStackTrace();
        }
        return resultSet;
    }

    // Method to execute a prepared update
    public int executePreparedUpdate(String sql, Object... params) {
        int affectedRows = 0;
        try {
            Connection conn = getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            // Set parameters
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            affectedRows = preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error executing prepared update: " + e.getMessage());
            e.printStackTrace();
        }
        return affectedRows;
    }
}
