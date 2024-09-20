package com.example.odc.repositories.collection;

import com.example.odc.entities.*;
import com.example.odc.enums.Role;
import com.example.odc.repositories.ClientIRepository;
import com.example.odc.repositories.CollectionIRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ClientRepoImplCollection extends CollectionIRepository<Client> implements ClientIRepository {
    public final Collection<User> collectionUser;
    public final Collection<Client> collectionClient;


    public ClientRepoImplCollection(
                                     @Qualifier("myClients") Collection<Client> collectionClient,
                                     @Qualifier("myUsers") Collection<User> collectionUser) {
        super(collectionClient);
        resolveEntityType();
        this.collectionUser = collectionUser;
        this.collectionClient = collectionClient;
    }


    @Override
    public Client findBySurnom(String surnom) {
        return this.collectionClient.stream()
               .filter(client -> client.getSurnom().equals(surnom))
               .findFirst()
               .orElse(null);
    }

    @Override
    public Client findByTelephone(String telephone) {
        return this.collectionClient.stream()
               .filter(client -> client.getTelephone().equals(telephone))
               .findFirst()
               .orElse(null);
    }

    @Override
    public Client createAccount(Client client) {
        User newUser = User.builder()
                .id(client.getId())
                .nom(client.getUser().getNom())
                .prenom(client.getUser().getPrenom())
                .password(client.getUser().getPassword())
                .login(client.getUser().getLogin())
                .role(Role.CLIENT)
                .build();

        boolean userExists = collectionUser.stream()
                .anyMatch(user -> user.getId() == newUser.getId());

        if (!userExists) {
            collectionUser.add(newUser);
        }

        boolean clientExists = collectionClient.stream()
                .anyMatch(existingClient -> existingClient.getId() == client.getId());

        if (clientExists) {
            collectionClient.removeIf(existingClient -> existingClient.getId() == client.getId());
            collectionClient.add(client);
        } else {
            collectionClient.add(client);
        }
        return client;
    }
}
