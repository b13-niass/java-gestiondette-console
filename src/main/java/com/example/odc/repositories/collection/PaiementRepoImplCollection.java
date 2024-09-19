package com.example.odc.repositories.collection;

import com.example.odc.entities.*;
import com.example.odc.repositories.CollectionIRepository;
import com.example.odc.repositories.PaiementIRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Component
public class PaiementRepoImplCollection  extends CollectionIRepository<Paiement> implements PaiementIRepository {
    private final Collection<ArticleDette> collectionArticleDette;
    private final Collection<Article> collectionArticle;
    private final Collection<Dette> collectionDette;
    public final Collection<User> collectionUser;
    private final Collection<Paiement> collectionPaiement;
    public final Collection<Client> collectionClient;


    public PaiementRepoImplCollection(@Qualifier("myArticles") Collection<Article> collectionArticle,
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
    public Collection<Paiement> findByDette(int detteId) {
        // Filtrer les paiements associés à une dette donnée par son id
        return collectionPaiement.stream()
                .filter(paiement -> paiement.getDette().getId() == detteId)
                .toList();
    }

}
