package com.example.odc.services.impl;

import com.example.odc.entities.Client;
import com.example.odc.repositories.ClientIRepository;
import com.example.odc.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Component
public class ClientServiceImp implements ClientService {
    private final ClientIRepository clientRepository;

    @Autowired
    public ClientServiceImp(@Qualifier("clientRepoImplCollection") ClientIRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Collection<Client> all() {
        return this.clientRepository.findAll();
    }

    @Override
    public Client find(int id) {
        return this.clientRepository.find(id);
    }

    @Override
    public int save(Client entity) {
        return this.clientRepository.save(entity);
    }

    @Override
    public int delete(int id) {
        return this.clientRepository.delete(id);
    }

    @Override
    public int update(int id, Client entity) {
        return this.clientRepository.update(id, entity);
    }

    @Override
    public Client findBySurnom(String surnom) {
        return this.clientRepository.findBySurnom(surnom);
    }

    @Override
    public Client findByTelephone(String telephone) {
        return this.clientRepository.findByTelephone(telephone);
    }

    @Override
    public Client createAccount(Client client) {
        return this.clientRepository.createAccount(client);
    }
}
