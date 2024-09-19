package com.example.odc.services;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface IService<T> {
    public Collection<T> all();
    public T find(int id);
    public int save(T entity);
    public int delete(int id);
    public int update(int id, T entity);
}
