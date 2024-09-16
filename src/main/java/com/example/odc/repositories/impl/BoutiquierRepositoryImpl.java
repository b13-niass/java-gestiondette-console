package com.example.odc.repositories.impl;

import com.example.odc.entities.ModelFactory;
import com.example.odc.entities.impl.*;
import com.example.odc.repositories.BoutiquierRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BoutiquierRepositoryImpl implements BoutiquierRepository {
    @Override
    public List<Client> all() {
        return ModelFactory.createClient().all();
    }

    @Override
    public Client find(int id) {
        List<Client> clients = ModelFactory.createClient().all();
        return clients.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Article findArticle(int id) {
        List<Article> articles = ModelFactory.createArticle().all();
        return articles.stream().filter(a -> a.getId() == id).findFirst().orElse(null);
    }


    @Override
    public Client findByTelephone(String telephone) {
        List<Client> clients = ModelFactory.createClient().all();
        return clients.stream().filter(c -> c.getTelephone().equalsIgnoreCase(telephone)).findFirst().orElse(null);
    }

    @Override
    public Client findBySurnom(String surname) {
        List<Client> clients = ModelFactory.createClient().all();
        return clients.stream().filter(c -> c.getSurnom().equalsIgnoreCase(surname)).findFirst().orElse(null);
    }

    @Override
    public Dette findDetteById(int id) {
        List<Dette> debts = ModelFactory.createDette().all();
        return debts.stream().filter(d -> d.getId() == id).findFirst().orElse(null);
    }

    @Override
    public Client create(Client client) {
        return ModelFactory.createClient().create(client);
    }

    @Override
    public Client update(int id, Client client) {
        return ModelFactory.createClient().update(id, client);
    }

    @Override
    public void delete(int id) {
        ModelFactory.createClient().delete(id);
    }

    @Override
    public Client createCompte(Client client, User user) {
        ModelFactory.createUser().create(user);
        Client client1 = this.find(client.getId());
        client1.setUser(user);
        return ModelFactory.createClient().update(client1.getId(), client1);
    }

    @Override
    public Dette createDebt(Dette dette, List<ArticleDette> articleDettes, Optional<Paiement> paiement) {

    Dette dette2 = ModelFactory.createDette().create(dette);
    articleDettes.stream().forEach(articleDette ->{
        articleDette.setDette(dette2);
        ModelFactory.createArticleDette().create(articleDette);
    });
//        if(paiement.isPresent()){
//            Paiement paiement1 = ModelFactory.createPaiement().create(paiement.get());
//            paiement1.setDette(dette2);
//            ModelFactory.createPaiement().update(paiement1.getId(),paiement1);
//        }
        return dette2;
    }

    @Override
    public List<Dette> getDette(Client client) {
        List<Dette> dettes = ModelFactory.createDette().all();
        return dettes.stream().filter(d -> d.getClient().getId() == client.getId()).collect(Collectors.toList());
    }

    @Override
    public List<Paiement> getPaiement(Dette dette) {
        List<Paiement> paiements = ModelFactory.createPaiement().all();
        return paiements.stream().filter(p -> p.getDette().getId() == dette.getId()).collect(Collectors.toList());
    }

    @Override
    public double getMontantVerser(int id) {
        List<Paiement> paiements = ModelFactory.createPaiement().all();
        return paiements.stream().filter(p -> p.getDette().getId() == id).mapToDouble(Paiement::getMontant).sum();
    }

    @Override
    public double getMontantDu(int id) {
        Dette dette = this.findDetteById(id);
        return dette.getMontant() - this.getMontantVerser(id);
    }

    @Override
    public Paiement effectuerPaiement(Paiement paiement) {
        return ModelFactory.createPaiement().create(paiement);
    }

}
