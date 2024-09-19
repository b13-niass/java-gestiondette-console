package com.example.odc.services.impl;

import com.example.odc.entities.ArticleDette;
import com.example.odc.entities.Dette;
import com.example.odc.entities.Paiement;
import com.example.odc.repositories.DetteIRepository;
import com.example.odc.services.DetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class DetteServiceImp implements DetteService {
    private final DetteIRepository repository;

    @Autowired
    public DetteServiceImp(@Qualifier("detteRepoImplCollection") DetteIRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Dette> all() {
        return this.repository.findAll();
    }

    @Override
    public Dette find(int id) {
        return this.repository.find(id);
    }

    @Override
    public int save(Dette entity) {
        return 0;
    }

    @Override
    public int save(Dette entity, Collection<ArticleDette> articleDettes, Optional<Paiement> paiementOptional) {
        return this.repository.saveDette(entity,articleDettes,paiementOptional);
    }

    @Override
    public Collection<Dette> findByClient(int id) {
        return this.repository.findByClient(id);
    }

    @Override
    public int delete(int id) {
        return this.repository.delete(id);
    }

    @Override
    public int update(int id, Dette entity) {
        return this.repository.update(id, entity);
    }

    @Override
    public double findMontantDu(int id) {
        return this.repository.findMontantDu(id);
    }

    @Override
    public double findMontantVerser(int id) {
        return this.repository.findMontantVerser(id);
    }
}
