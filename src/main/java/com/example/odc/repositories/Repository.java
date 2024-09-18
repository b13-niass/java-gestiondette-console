package com.example.odc.repositories;

import java.util.Collection;

public interface Repository<T> {
        int save(T entity);
        Collection<T> findAll();
        T find(int id);
        int update(int id, T entity);
        int delete(int id);
}
