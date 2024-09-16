package com.example.odc.services;

import com.example.odc.entities.impl.*;

import java.util.List;
import java.util.Optional;

public interface BoutiquierService {
    public List<Client> all();
    public Client find(int id);
    public Article findArticle(int id);
    public Client findByTelephone(String telephone);
    public Client findBySurnom(String surname);
    public Dette findDetteById(int id);
    public Client create(Client client);
    public Client update(int id, Client client);
    public void delete(int id);
    public Client createCompte(Client client, User user);
    public Dette createDebt(Client client, Dette dette, List<ArticleDette> articleDettes, Optional<Paiement> paiement );
    public List<Dette> getDette(Client client);
    public List<Paiement> getPaiement(Dette dette);
    public double getMontantVerser(int id);
    public double getMontantDu(int id);
    public Paiement effectuerPaiement(Paiement paiement);
}
