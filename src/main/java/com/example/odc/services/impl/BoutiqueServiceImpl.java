package com.example.odc.services.impl;

import com.example.odc.entities.impl.*;
import com.example.odc.repositories.BoutiquierRepository;

import java.util.List;

public class BoutiqueServiceImpl implements BoutiquierRepository {
    @Override
    public List<Client> all() {
        return List.of();
    }

    @Override
    public Client find(int id) {
        return null;
    }

    @Override
    public void create(Client client) {

    }

    @Override
    public void update(int id, Client client) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void createCompte(Client client, User user) {

    }

    @Override
    public void createDebt(Client client, Dette dette, Article article) {

    }

    @Override
    public List<Dette> getDette(Client client) {
        return List.of();
    }

    @Override
    public List<Paiement> getPaiement(Dette dette) {
        return List.of();
    }
}
