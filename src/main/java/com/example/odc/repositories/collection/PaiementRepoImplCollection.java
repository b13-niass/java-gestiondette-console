package com.example.odc.repositories.collection;

import com.example.odc.entities.Dette;
import com.example.odc.entities.Paiement;
import com.example.odc.repositories.CollectionRepository;
import com.example.odc.repositories.DetteRepository;
import com.example.odc.repositories.PaiementRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Profile("collection")
@Repository
public class PaiementRepoImplCollection  extends CollectionRepository<Paiement> implements PaiementRepository {
    private final Collection collection;
    public PaiementRepoImplCollection(Collection<Paiement> collection) {
        super(collection);
        this.collection = collection;
    }

    @Override
    public Collection<Paiement> findByDette(int id) {
        return List.of();
    }
}
