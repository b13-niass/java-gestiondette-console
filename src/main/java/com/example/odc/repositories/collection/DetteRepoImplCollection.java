package com.example.odc.repositories.collection;

import com.example.odc.entities.Article;
import com.example.odc.entities.Dette;
import com.example.odc.repositories.CollectionRepository;
import com.example.odc.repositories.DetteRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Profile("collection")
@Repository
public class DetteRepoImplCollection extends CollectionRepository<Dette> implements DetteRepository {
    private final Collection collection;
    public DetteRepoImplCollection(Collection<Dette> collection) {
        super(collection);
         this.collection = collection;
    }

    @Override
    public double findMontantDu(int id) {
        return 0;
    }

    @Override
    public double findMontantVerser(int id) {
        return 0;
    }
}
