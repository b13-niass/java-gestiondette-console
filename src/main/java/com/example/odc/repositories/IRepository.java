package com.example.odc.repositories;

import java.sql.SQLException;
import java.util.Collection;

public interface IRepository<T> {
        int save(T entity);
        Collection<T> findAll();
        T find(int id);
        int update(int id, T entity);
        int delete(int id);
}
