package com.example.odc.repositories.collection;

import com.example.odc.entities.*;
import com.example.odc.repositories.CollectionIRepository;
import com.example.odc.repositories.PaiementIRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class PaiementRepoImplCollection  extends CollectionIRepository<Paiement> implements PaiementIRepository {

    private final Collection<Paiement> collectionPaiement;

    public PaiementRepoImplCollection(@Qualifier("myPaiement") Collection<Paiement> collectionPaiement) {
        super(collectionPaiement);
        resolveEntityType();
        this.collectionPaiement = collectionPaiement;
    }


    @Override
    public Collection<Paiement> findByDette(int detteId) {
        return collectionPaiement.stream()
                .filter(paiement -> paiement.getDette().getId() == detteId)
                .toList();
    }

}
