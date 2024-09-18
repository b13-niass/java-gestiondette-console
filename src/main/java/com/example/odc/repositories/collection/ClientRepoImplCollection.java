package com.example.odc.repositories.collection;

import com.example.odc.entities.Article;
import com.example.odc.entities.Client;
import com.example.odc.repositories.ClientRepository;
import com.example.odc.repositories.CollectionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Profile("collection")

@Repository
public class ClientRepoImplCollection extends CollectionRepository<Client> implements ClientRepository {
    private final Collection collection;
    public ClientRepoImplCollection(Collection<Client> collection) {
        super(collection);
      this.collection = collection;
    }

    @Override
    public Client findBySurnom(String surnom) {
        return null;
    }

    @Override
    public Client findByTelephone(String telephone) {
        return null;
    }

    @Override
    public Client createAccount(Client client) {
        return null;
    }
}
