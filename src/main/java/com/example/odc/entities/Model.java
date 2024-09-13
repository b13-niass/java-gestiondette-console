package com.example.odc.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Model<T> {

    protected List<T> data = new ArrayList<>();
    protected int currentId = 1;

    public Model(List<T> data) {
        this.data = data;
    }

    public List<T> all() {
        return new ArrayList<>(data);
    }

    public T find(int id) {
        return data.stream()
                .filter(entity -> getId(entity) == id)
                .findFirst()
                .orElse(null);
    }

    public void create(T entity) {
        setId(entity, currentId++);
        data.add(entity);
    }

    public void update(int id, T entity) {
        Optional<T> existingEntity = data.stream()
                .filter(e -> getId(e) == id)
                .findFirst();

        existingEntity.ifPresent(e -> {
            int index = data.indexOf(e);
            setId(entity, id);
            data.set(index, entity);
        });
    }

    public void delete(int id) {
        data.removeIf(entity -> getId(entity) == id);
    }

    protected abstract int getId(T entity);
    protected abstract void setId(T entity, int id);
}
