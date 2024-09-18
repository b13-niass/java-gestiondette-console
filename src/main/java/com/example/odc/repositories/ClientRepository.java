package com.example.odc.repositories;

import com.example.odc.entities.Client;

public interface ClientRepository extends Repository<Client> {
    public Client findBySurnom(String surnom);
    public Client findByTelephone(String telephone);
    public Client createAccount(Client client);
}
