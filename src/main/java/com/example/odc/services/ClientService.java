package com.example.odc.services;

import com.example.odc.entities.Client;
import org.springframework.stereotype.Component;

@Component
public interface ClientService extends IService<Client> {
    public Client findBySurnom(String surnom);
    public Client findByTelephone(String telephone);
    public Client createAccount(Client client);
}
