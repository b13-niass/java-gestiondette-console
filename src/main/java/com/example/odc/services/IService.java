package com.example.odc.services;

import java.util.Collection;

public interface IService<T> {
    public Collection<T> all();
    public T find(int id);
    public int save(T entity);
    public int delete(T entity);
    public int update(int id, T entity);
}
