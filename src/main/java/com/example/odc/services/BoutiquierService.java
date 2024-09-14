package com.example.odc.services;

import com.example.odc.entities.impl.*;

import java.util.List;

public interface BoutiquierService {
    public List<Client> all();
    public Client find(int id);
    public void create(Client client);
    public void update(int id, Client client);
    public void delete(int id);
    public void createCompte(Client client, User user);
    public void createDebt(Client client, Dette dette, Article article);
    public List<Dette> getDette(Client client);
    public List<Paiement> getPaiement(Dette dette);
}
