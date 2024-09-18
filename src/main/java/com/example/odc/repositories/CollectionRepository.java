package com.example.odc.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Optional;

@Component
public class CollectionRepository<T> implements Repository<T>{

    private final Collection<T> collection;
    private final Class<T> entityClass;

    @SuppressWarnings("unchecked")
    public CollectionRepository(@Qualifier("myCollection") Collection<T> collection) {
        this.collection = collection;
        this.entityClass = this.getGenericClass();
    }

    @Override
    public int save(T entity) {
        if(collection.add(entity))
            return 1;
        else
            return 0;
    }

    @Override
    public Collection<T> findAll() {
        System.out.println("Retrieving all entities of type: " + getEntityName());
        return collection;
    }

    @Override
    public T find(int id) {
        Optional<T> foundEntity = collection.stream()
                .filter(entity -> getIdValue(entity) == id)
                .findFirst();
        return foundEntity.orElse(null);
    }

    @Override
    public int update(int id, T entity) {
        T existingEntity = find(id);
        if (existingEntity != null) {
            collection.remove(existingEntity);
            collection.add(entity); // Replace the existing entity with the new one
            return 1;
        }
        return 0;
    }

    @Override
    public int delete(int id) {
        T entityToDelete = find(id);
        if (entityToDelete != null) {
            collection.remove(entityToDelete);
            return 1;
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

    // Get the entity class name dynamically
    private String getEntityName() {
        return entityClass.getSimpleName();
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
