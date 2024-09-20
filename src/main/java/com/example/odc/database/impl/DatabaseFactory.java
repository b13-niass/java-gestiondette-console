package com.example.odc.database.impl;

import com.example.odc.database.Database;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.math.BigDecimal;
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

    // Method to execute a prepared query
    public <T> T executePreparedQuery(String sql,Class<T> entityClass,  Object... params) throws SQLException {
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
        return this.mapResultSetToEntity(resultSet, entityClass);
    }

    // Method to execute a prepared update
    public int executePreparedUpdate(String sql, Object... params) {
        int lastInsertedId = -1;
        try {
            Connection conn = getConnection();
            // Enable return of generated keys
            PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        lastInsertedId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Error executing prepared update: " + e.getMessage());
            e.printStackTrace();
        }
        return lastInsertedId;
    }


    private <T> T mapResultSetToEntity(ResultSet rs, Class<T> entityClass) throws SQLException {
        try {
            T entity = entityClass.getDeclaredConstructor().newInstance();
            Field[] fields = entityClass.getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                String columnName = field.getName();

                Object value = rs.getObject(columnName);

                if (field.getType() == double.class && value instanceof BigDecimal) {
                    value = ((BigDecimal) value).doubleValue();
                }

                try {
                    field.set(entity, value);
                } catch (IllegalAccessException e) {
                    throw new SQLException("Error setting value for field: " + columnName, e);
                }
            }
            return entity;
        } catch (Exception e) {
            throw new SQLException("Error mapping ResultSet to entity: " + e.getMessage(), e);
        }
    }

}
