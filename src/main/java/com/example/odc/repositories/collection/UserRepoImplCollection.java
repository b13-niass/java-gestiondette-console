package com.example.odc.repositories.collection;

import com.example.odc.entities.*;
import com.example.odc.repositories.CollectionIRepository;
import com.example.odc.repositories.UserIRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Component
public class UserRepoImplCollection extends CollectionIRepository<User> implements UserIRepository {
    private final Collection<ArticleDette> collectionArticleDette;
    private final Collection<Article> collectionArticle;
    private final Collection<Dette> collectionDette;
    public final Collection<User> collectionUser;
    private final Collection<Paiement> collectionPaiement;
    public final Collection<Client> collectionClient;


    public UserRepoImplCollection(@Qualifier("myArticles") Collection<Article> collectionArticle,
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
    public boolean authenticate(String username, String password) {
        User user = this.collectionUser.stream().filter(user1 -> user1.getLogin().equals(username) && user1.getPassword().equals(password)).findFirst().orElse(null);
        return user != null;
    }

    @Override
    public User findByLogin(String login) {
        return this.collectionUser.stream()
               .filter(user -> user.getLogin().equals(login))
               .findFirst()
               .orElse(null);
    }
}
