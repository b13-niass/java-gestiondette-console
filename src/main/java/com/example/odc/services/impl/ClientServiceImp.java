package com.example.odc.services.impl;

import com.example.odc.entities.Client;
import com.example.odc.repositories.ClientRepository;
import com.example.odc.services.ClientService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ClientServiceImp implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImp(@Qualifier("collection") ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Collection<Client> all() {
        return List.of();
    }

    @Override
    public Client find(int id) {
        return null;
    }

    @Override
    public int save(Client entity) {
        return 0;
    }

    @Override
    public int delete(Client entity) {
        return 0;
    }

    @Override
    public int update(int id, Client entity) {
        return 0;
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
