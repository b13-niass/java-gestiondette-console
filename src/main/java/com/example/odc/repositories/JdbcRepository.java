package com.example.odc.repositories;

import com.example.odc.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class JdbcRepository<T> implements Repository<T> {

    protected String tablename;
    private final Database database;
    private Class<T> entityClass;

    @Autowired
    public JdbcRepository(Database database) {
        this.database = database;
        this.entityClass = this.getGenericClass();
        this.tablename = this.getGenericClass().getSimpleName().toLowerCase();
    }

//    private Class<T> getEntity(){
//        return (Class<T>) ((ParameterizedType) getClass()
//                .getGenericSuperclass())
//                .getActualTypeArguments()[0];
//    }

    @Override
    public int save(T entity) {
        StringBuilder query = new StringBuilder("INSERT INTO " + tablename + " (");
        StringBuilder valuesPart = new StringBuilder("VALUES (");

        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            query.append(fields[i].getName());
            valuesPart.append("?");
            if (i < fields.length - 1) {
                query.append(", ");
                valuesPart.append(", ");
            }
        }
        query.append(") ");
        valuesPart.append(")");
        query.append(valuesPart);

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            for (int i = 0; i < fields.length; i++) {
                stmt.setObject(i + 1, fields[i].get(entity));
            }

           return stmt.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Collection<T> findAll() {
        Collection<T> results = new ArrayList<>();
        String query = "SELECT * FROM " + tablename;
        try (Connection conn = database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                T entity = mapResultSetToEntity(rs);
                results.add(entity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public T find(int id) {
        T entity = null;
        String query = "SELECT * FROM " + tablename + " WHERE id = ?";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                entity = mapResultSetToEntity(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entity;
    }

    @Override
    public int update(int id, T entity) {
        StringBuilder query = new StringBuilder("UPDATE " + tablename + " SET ");
        Field[] fields = entityClass.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            query.append(fields[i].getName()).append(" = ?");
            if (i < fields.length - 1) {
                query.append(", ");
            }
        }
        query.append(" WHERE id = ?");

        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query.toString())) {

            for (int i = 0; i < fields.length; i++) {
                stmt.setObject(i + 1, fields[i].get(entity));
            }
            stmt.setInt(fields.length + 1, id);

            return stmt.executeUpdate();
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM " + tablename + " WHERE id = ?";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
           return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private T mapResultSetToEntity(ResultSet rs) throws SQLException {
        try {
            T entity = entityClass.getDeclaredConstructor().newInstance();
            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                Object value = rs.getObject(field.getName());
                field.set(entity, value);
            }
            return entity;
        } catch (Exception e) {
            throw new SQLException("Error mapping ResultSet to entity", e);
        }
    }

    @SuppressWarnings("unchecked")
    public Class<T> getGenericClass() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (type instanceof Class) {
                return (Class<T>) type;
            } else {
                throw new IllegalStateException("The type parameter is not a class");
            }
        } else {
            throw new IllegalStateException("The superclass is not parameterized");
        }
    }
}
