package com.example.odc.services.impl;

import com.example.odc.entities.impl.*;
import com.example.odc.repositories.BoutiquierRepository;
import com.example.odc.services.BoutiquierService;

import java.util.List;
import java.util.Optional;

public class BoutiquierServiceImpl implements BoutiquierService {

    private BoutiquierRepository repository;

    public BoutiquierServiceImpl(BoutiquierRepository repository) {
        this.repository = repository;
    }


    @Override
    public List<Client> all() {
        return repository.all();
    }

    @Override
    public Client find(int id) {
        return repository.find(id);
    }

    @Override
    public Article findArticle(int id) {
        return  repository.findArticle(id);
    }

    @Override
    public Client findByTelephone(String telephone) {
       return repository.findByTelephone(telephone);
    }

    @Override
    public Client findBySurnom(String surname) {
        return repository.findBySurnom(surname);
    }

    @Override
    public Dette findDetteById(int id) {
        return this.repository.findDetteById(id);
    }

    @Override
    public Client create(Client client) {
        return repository.create(client);
    }

    @Override
    public Client update(int id, Client client) {
        return repository.update(id, client);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }

    @Override
    public Client createCompte(Client client, User user) {
        return repository.createCompte(client, user);
    }

    @Override
    public Dette createDebt(Client client, Dette dette, List<ArticleDette> articleDettes, Optional<Paiement> paiement) {
        return repository.createDebt(client, dette, articleDettes, paiement);
    }

    @Override
    public List<Dette> getDette(Client client) {
        return repository.getDette(client);
    }

    @Override
    public List<Paiement> getPaiement(Dette dette) {
        return repository.getPaiement(dette);
    }
}
