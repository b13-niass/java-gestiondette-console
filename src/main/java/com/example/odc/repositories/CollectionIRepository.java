package com.example.odc.repositories;

import com.example.odc.entities.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;

@Component
public class CollectionIRepository<T> implements IRepository<T> {

    private final Collection<T> collection;

    private Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public CollectionIRepository(Collection<T> collection) {
        this.collection = collection;
    }


    @Override
    public int save(T entity) {
        if (collection != null && collection.add(entity)) {
            return 1; // Successfully added
        }
        return 0; // Entity already exists or collection is null
    }

    @Override
    public Collection<T> findAll() {
        if (collection != null) {
            return collection;
        }
        return null;
    }

    @Override
    public T find(int id) {
        if (collection != null) {
            Optional<T> foundEntity = collection.stream()
                    .filter(entity -> getIdValue(entity) == id)
                    .findFirst();
            return foundEntity.orElse(null);
        }
        return null;
    }

    @Override
    public int update(int id, T entity) {
        if (collection != null) {
            T existingEntity = find(id);
            if (existingEntity != null) {
                collection.remove(existingEntity);
                collection.add(entity); // Replace the existing entity with the new one
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        if (collection != null) {
            T entityToDelete = find(id);
            if (entityToDelete != null) {
                collection.remove(entityToDelete);
                return 1;
            }
        }
        return 0;
    }

    // Utility method to extract the 'id' field value from the entity
    private int getIdValue(T entity) {
        try {
            var idField = entityClass.getDeclaredField("id");
            idField.setAccessible(true);
            return (int) idField.get(entity);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException("Unable to get 'id' field from entity.", e);
        }
    }

    // Resolve the entity type using reflection
    @SuppressWarnings("unchecked")
    protected void resolveEntityType() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            Type type = parameterizedType.getActualTypeArguments()[0];
            if (type instanceof Class) {
                this.entityClass = (Class<T>) type;
            } else {
                throw new IllegalStateException("The type parameter is not a class");
            }
        } else {
            throw new IllegalStateException("The superclass is not parameterized");
        }
    }
}
