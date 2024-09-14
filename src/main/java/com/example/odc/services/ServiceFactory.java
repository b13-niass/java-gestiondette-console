package com.example.odc.services;

import com.example.odc.repositories.BoutiquierRepository;
import com.example.odc.repositories.ClientRepository;
import com.example.odc.repositories.RepositoryFactory;
import com.example.odc.repositories.UserRepository;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    private static final Map<Class<?>, Object> services = new HashMap<>();

    public static <T> T createService(Class<T> serviceInterface) {
        if (!services.containsKey(serviceInterface)) {
            try {
                String serviceName = serviceInterface.getSimpleName();
                String baseName = serviceName.replace("Service", "");
                String repositoryInterfaceName = baseName + "Repository";

                String repositoryPackageName = serviceInterface.getPackage().getName().replace("services", "repositories");
                String fullyQualifiedRepositoryInterfaceName = repositoryPackageName + "." + repositoryInterfaceName;

                Class<?> repositoryInterfaceClass = Class.forName(fullyQualifiedRepositoryInterfaceName);
                Object repository = RepositoryFactory.createRepository(repositoryInterfaceClass);

                String serviceImplName = baseName + "ServiceImpl";
                String fullyQualifiedServiceImplName = serviceInterface.getPackage().getName() + ".impl." + serviceImplName;

                Class<?> serviceImplClass = Class.forName(fullyQualifiedServiceImplName);
                T service = (T) serviceImplClass.getConstructor(repositoryInterfaceClass).newInstance(repository);

                services.put(serviceInterface, service);
            } catch (Exception e) {
                throw new RuntimeException("Impossible de créer le service pour " + serviceInterface.getName(), e);
            }
        }
        return (T) services.get(serviceInterface);
    }
}
