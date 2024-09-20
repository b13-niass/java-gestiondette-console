package com.example.odc.repositories;

import com.example.odc.database.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Component
public class JdbcIRepository<T> implements IRepository<T> {

    protected String tablename;
    private final Database database;
    private Class<T> entityClass;

    @Autowired
    public JdbcIRepository(Database database) {
        this.database = database;
    }

    @SuppressWarnings("unchecked")
    protected void resolveEntityType() {
        Type genericSuperclass = getClass().getGenericSuperclass();

        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type type = parameterizedType.getActualTypeArguments()[0];

            if (type instanceof Class) {
                this.entityClass = (Class<T>) type;
                this.tablename = this.entityClass.getSimpleName().toLowerCase()+"s";
            } else {
                throw new IllegalStateException("The type parameter is not a class");
            }
        } else {
            throw new IllegalStateException("The superclass is not parameterized");
        }
    }

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

       return this.database.executePreparedUpdate(query.toString(), entity);
    }

    @Override
    public Collection<T> findAll() {
        Collection<T> results;
        String query = "SELECT * FROM " + tablename;
        try {
            results = Collections.singleton(this.database.executePreparedQuery(query, this.entityClass));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public T find(int id) {
        T entity = null;
        String query = "SELECT * FROM " + tablename + " WHERE id = ?";
        try {
            entity = this.database.executePreparedQuery(query, this.entityClass);
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
        return this.database.executePreparedUpdate(query.toString(),id,entity);
    }

    @Override
    public int delete(int id) {
        String query = "DELETE FROM " + tablename + " WHERE id = ?";
        return this.database.executePreparedUpdate(query, id);
    }

}
