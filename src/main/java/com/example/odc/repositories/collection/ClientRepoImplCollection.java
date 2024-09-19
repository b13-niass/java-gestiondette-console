package com.example.odc.repositories.collection;

import com.example.odc.entities.*;
import com.example.odc.enums.Role;
import com.example.odc.repositories.ClientIRepository;
import com.example.odc.repositories.CollectionIRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Component
public class ClientRepoImplCollection extends CollectionIRepository<Client> implements ClientIRepository {
    private final Collection<ArticleDette> collectionArticleDette;
    private final Collection<Article> collectionArticle;
    private final Collection<Dette> collectionDette;
    public final Collection<User> collectionUser;
    private final Collection<Paiement> collectionPaiement;
    public final Collection<Client> collectionClient;


    public ClientRepoImplCollection(@Qualifier("myArticles") Collection<Article> collectionArticle,
                                     @Qualifier("myClients") Collection<Client> collectionClient,
                                     @Qualifier("myDettes") Collection<Dette> collectionDette,
                                     @Qualifier("myUsers") Collection<User> collectionUser,
                                     @Qualifier("myArticleDette") Collection<ArticleDette> collectionArticleDette,
                                     @Qualifier("myPaiement") Collection<Paiement> collectionPaiement) {
        super(collectionArticle,collectionClient,collectionDette,collectionUser,collectionArticleDette,collectionPaiement);
        resolveEntityType();
        this.collectionArticleDette = collectionArticleDette;
        this.collectionArticle = collectionArticle;
        this.collectionDette = collectionDette;
        this.collectionUser = collectionUser;
        this.collectionPaiement = collectionPaiement;
        this.collectionClient = collectionClient;
    }


    @Override
    public Client findBySurnom(String surnom) {
        // using stream
        return this.collectionClient.stream()
               .filter(client -> client.getSurnom().equals(surnom))
               .findFirst()
               .orElse(null);
    }

    @Override
    public Client findByTelephone(String telephone) {
        // using stream
        return this.collectionClient.stream()
               .filter(client -> client.getTelephone().equals(telephone))
               .findFirst()
               .orElse(null);
    }

    @Override
    public Client createAccount(Client client) {
        User newUser = User.builder()
                .id(client.getId())
                .nom(client.getUser().getNom())
                .prenom(client.getUser().getPrenom())
                .password(client.getUser().getPassword())
                .login(client.getUser().getLogin())
                .role(Role.CLIENT)
                .build();

        boolean userExists = collectionUser.stream()
                .anyMatch(user -> user.getId() == newUser.getId());

        if (!userExists) {
            collectionUser.add(newUser);
        }

        boolean clientExists = collectionClient.stream()
                .anyMatch(existingClient -> existingClient.getId() == client.getId());

        if (clientExists) {
            collectionClient.removeIf(existingClient -> existingClient.getId() == client.getId());
            collectionClient.add(client);
        } else {
            collectionClient.add(client);
        }

        return client;
    }

}
