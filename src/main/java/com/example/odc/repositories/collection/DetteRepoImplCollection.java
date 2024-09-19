package com.example.odc.repositories.collection;

import com.example.odc.entities.*;
import com.example.odc.repositories.CollectionIRepository;
import com.example.odc.repositories.DetteIRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DetteRepoImplCollection extends CollectionIRepository<Dette> implements DetteIRepository {
    private final Collection<ArticleDette> collectionArticleDette;
    private final Collection<Article> collectionArticle;
    private final Collection<Dette> collectionDette;
    public final Collection<User> collectionUser;
    private final Collection<Paiement> collectionPaiement;
    public final Collection<Client> collectionClient;


    public DetteRepoImplCollection(@Qualifier("myArticles") Collection<Article> collectionArticle,
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

    public int saveDette(Dette entity, Collection<ArticleDette> articleDettes, Optional<Paiement> paiementOptional){
        int id = super.save(entity);
        for(ArticleDette articleDette : articleDettes){
            articleDette.setDette(this.find(id));
            collectionArticleDette.add(articleDette);
        }
        if(paiementOptional.isPresent()){
            paiementOptional.get().setDette(this.find(id));
            collectionPaiement.add(paiementOptional.get());
        }
        return id;
    }

    @Override
    public double findMontantDu(int detteId) {
        return this.find(detteId).getMontant() - this.findMontantVerser(detteId);
    }

    @Override
    public double findMontantVerser(int detteId) {
        return collectionPaiement.stream()
                .filter(paiement -> paiement.getDette().getId() == detteId)
                .mapToDouble(Paiement::getMontant)
                .sum();
    }

    @Override
    public Collection<Dette> findByClient(int id) {
        return collectionDette.stream()
                .filter(dette -> dette.getClient().getId() == id)
                .collect(Collectors.toList());
    }

}
