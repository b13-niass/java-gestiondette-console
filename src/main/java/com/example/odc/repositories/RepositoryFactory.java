package com.example.odc.repositories;

import java.util.HashMap;
import java.util.Map;

public class RepositoryFactory {

    // Cache pour stocker les instances singleton
    private static final Map<Class<?>, Object> repositoryCache = new HashMap<>();

    public static <T> T createRepository(Class<T> repositoryInterface) {
        if (repositoryCache.containsKey(repositoryInterface)) {
            return (T) repositoryCache.get(repositoryInterface);
        }
        try {
            String interfaceName = repositoryInterface.getSimpleName();
            String implClassName = interfaceName + "Impl"; // e.g., ClientRepository -> ClientRepositoryImpl
            String packageName = repositoryInterface.getPackage().getName();
            String fullyQualifiedClassName = packageName + ".impl." + implClassName;
            Class<?> implClass = Class.forName(fullyQualifiedClassName);
            if (!repositoryInterface.isAssignableFrom(implClass)) {
                throw new IllegalArgumentException(implClass.getName() + " ne correspond pas à " + repositoryInterface.getName());
            }
            T repositoryInstance = (T) implClass.getDeclaredConstructor().newInstance();
            repositoryCache.put(repositoryInterface, repositoryInstance);
            return repositoryInstance;
        } catch (Exception e) {
            throw new RuntimeException("Impossible de créer le repository pour " + repositoryInterface.getName(), e);
        }
    }
}
