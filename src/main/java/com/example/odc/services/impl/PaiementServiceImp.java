package com.example.odc.services.impl;

import com.example.odc.entities.Paiement;
import com.example.odc.repositories.PaiementIRepository;
import com.example.odc.services.PaiementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Component
public class PaiementServiceImp implements PaiementService {

    private final PaiementIRepository repository;

    public PaiementServiceImp(@Qualifier("paiementRepoImplCollection") PaiementIRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Paiement> all() {
        return this.repository.findAll();
    }

    @Override
    public Paiement find(int id) {
        return this.repository.find(id);
    }

    @Override
    public int save(Paiement entity) {
        return this.repository.save(entity);
    }

    @Override
    public int delete(int id) {
        return this.repository.delete(id);
    }

    @Override
    public int update(int id, Paiement entity) {
        return this.repository.update(id, entity);
    }

    @Override
    public Collection<Paiement> findByDette(int id) {
        return this.repository.findByDette(id);
    }
}
