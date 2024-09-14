package com.example.odc.services.impl;

import com.example.odc.entities.impl.Client;
import com.example.odc.entities.impl.Dette;
import com.example.odc.entities.impl.Paiement;
import com.example.odc.repositories.ClientRepository;

import java.util.List;

public class ClientServiceImpl implements ClientRepository {

    private ClientRepository repository;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public void payDebt(Dette dette, int amount) {

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
