package com.example.odc.config;

import com.example.odc.entities.*;
import com.example.odc.enums.Role;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class CollectionProvider {
    @Bean
    @Qualifier("myArticles")
    public static Collection<Article> getArticles() {
        Collection<Article> articles = new ArrayList<>();

        articles.add(Article.builder()
                .id(1)
                .libelle("Article A")
                .prixUnitaire(15.00)
                .quantiteEnStock(100)
                .build());

        articles.add(Article.builder()
                .id(2)
                .libelle("Article B")
                .prixUnitaire(25.00)
                .quantiteEnStock(50)
                .build());

        return articles;
    }

    @Bean
    @Qualifier("myClients")
    public static Collection<Client> getClients() {
        Collection<Client> clients = new ArrayList<>();

        User user1 = User.builder()
                .id(1)
                .nom("John")
                .prenom("Doe")
                .login("johndoe")
                .password("password123")
                .role(Role.CLIENT)
                .build();

        clients.add(Client.builder()
                .id(1)
                .surnom("Client1")
                .telephone("1234567890")
                .adresse("123 Main St")
                .user(user1)
                .build());

        return clients;
    }

    @Bean
    @Qualifier("myDettes")
    public static Collection<Dette> getDettes() {
        Collection<Dette> dettes = new ArrayList<>();

        Client client = Client.builder()
                .id(1)
                .surnom("Client1")
                .telephone("1234567890")
                .adresse("123 Main St")
                .user(User.builder().id(1).build())
                .build();

        dettes.add(Dette.builder()
                .id(1)
                .montant(100.00)
                .client(client)
                .build());

        return dettes;
    }

    @Bean
    @Qualifier("myUsers")
    public static Collection<User> getUsers() {
        Collection<User> users = new ArrayList<>();

        users.add(User.builder()
                .id(1)
                .nom("John")
                .prenom("Doe")
                .login("johndoe")
                .password("password123")
                .role(Role.BOUTIQUIER)
                .build());

        return users;
    }

    @Bean
    @Qualifier("myPaiement")
    public static Collection<Paiement> getPaiement() {
        Collection<Paiement> paiements = new ArrayList<>();

        Dette dette = Dette.builder()
                .id(1)
                .montant(100.00)
                .build();

        paiements.add(Paiement.builder()
                .id(1)
                .montant(50.00)
                .dette(dette)
                .build());

        return paiements;
    }

    @Bean
    @Qualifier("myArticleDette")
    public static Collection<ArticleDette> getArticleDette() {
        Collection<ArticleDette> articleDettes = new ArrayList<>();

        Article article = Article.builder()
                .id(1)
                .libelle("Article A")
                .prixUnitaire(15.00)
                .quantiteEnStock(100)
                .build();

        Dette dette = Dette.builder()
                .id(1)
                .montant(100.00)
                .build();

        articleDettes.add(ArticleDette.builder()
                .id(1)
                .prixVente(15.00)
                .qteVente(10)
                .article(article)
                .dette(dette)
                .build());

        return articleDettes;
    }

}
