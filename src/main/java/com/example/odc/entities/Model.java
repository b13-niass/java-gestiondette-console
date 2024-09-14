package com.example.odc.entities;

import com.example.odc.database.IDatabase;
import com.example.odc.entities.impl.JsonDataModel;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class Model<T> {

    protected List<T> data = null;
    protected JsonDataModel database;
    protected int currentId = 1;
    private Class<T> entityType;

    public Model(){}

    @SuppressWarnings("unchecked")
    public Model(IDatabase database) {
        setDatabase(database);
    }

    @SuppressWarnings("unchecked")
    private List<T> loadDataForEntityType() {
        try {
            // Construct the method name based on the entity type
            String methodName = "get" + entityType.getSimpleName() + "s";
            Method method = database.getClass().getMethod(methodName);
            return (List<T>) method.invoke(database);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
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

    public void setDatabase(IDatabase database) {
        this.database = (JsonDataModel) database.connect();

        Type genericSuperclass = getClass().getGenericSuperclass();

        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;

            Type type = parameterizedType.getActualTypeArguments()[0];

            if (type instanceof Class) {
                this.entityType = (Class<T>) type;
            }
        }

        this.data = loadDataForEntityType();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(getClass().getSimpleName()).append("{");

        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true); // Make private fields accessible
            try {
                result.append(field.getName())
                        .append("=")
                        .append(field.get(this))
                        .append(", ");
            } catch (IllegalAccessException e) {
                result.append(field.getName())
                        .append("=[inaccessible], ");
            }
        }

        if (fields.length > 0) {
            result.setLength(result.length() - 2);
        }

        result.append("}");
        return result.toString();
    }

    protected abstract int getId(T entity);
    protected abstract void setId(T entity, int id);
}
