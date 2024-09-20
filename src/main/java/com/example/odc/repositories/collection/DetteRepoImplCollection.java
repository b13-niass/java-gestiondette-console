package com.example.odc.repositories.collection;

import com.example.odc.entities.*;
import com.example.odc.repositories.CollectionIRepository;
import com.example.odc.repositories.DetteIRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class DetteRepoImplCollection extends CollectionIRepository<Dette> implements DetteIRepository {
    private final Collection<ArticleDette> collectionArticleDette;
    private final Collection<Dette> collectionDette;
    private final Collection<Paiement> collectionPaiement;


    public DetteRepoImplCollection(
            @Qualifier("myDettes") Collection<Dette> collectionDette,
            @Qualifier("myArticleDette") Collection<ArticleDette> collectionArticleDette,
            @Qualifier("myPaiement") Collection<Paiement> collectionPaiement) {
        super(collectionDette);
        resolveEntityType();
        this.collectionArticleDette = collectionArticleDette;
        this.collectionDette = collectionDette;
        this.collectionPaiement = collectionPaiement;
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
