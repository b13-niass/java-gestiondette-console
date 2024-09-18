package com.example.odc.services.impl;

import com.example.odc.entities.Paiement;
import com.example.odc.repositories.PaiementRepository;
import com.example.odc.services.PaiementService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class PaiementServiceImp implements PaiementService {

    private final PaiementRepository repository;

    public PaiementServiceImp(@Qualifier("collection") PaiementRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Paiement> all() {
        return List.of();
    }

    @Override
    public Paiement find(int id) {
        return null;
    }

    @Override
    public int save(Paiement entity) {
        return 0;
    }

    @Override
    public int delete(Paiement entity) {
        return 0;
    }

    @Override
    public int update(int id, Paiement entity) {
        return 0;
    }

    @Override
    public Collection<Paiement> findByDette(int id) {
        return List.of();
    }
}
