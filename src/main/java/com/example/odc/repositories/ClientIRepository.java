package com.example.odc.repositories;

import com.example.odc.entities.Client;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface ClientIRepository extends IRepository<Client> {
    public Client findBySurnom(String surnom);
    public Client findByTelephone(String telephone);
    public Client createAccount(Client client);
}
