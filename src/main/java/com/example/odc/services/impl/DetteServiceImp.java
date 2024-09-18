package com.example.odc.services.impl;

import com.example.odc.entities.Dette;
import com.example.odc.repositories.DetteRepository;
import com.example.odc.services.DetteService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class DetteServiceImp implements DetteService {
    private final DetteRepository repository;

    public DetteServiceImp(@Qualifier("collection") DetteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Dette> all() {
        return List.of();
    }

    @Override
    public Dette find(int id) {
        return null;
    }

    @Override
    public int save(Dette entity) {
        return 0;
    }

    @Override
    public int delete(Dette entity) {
        return 0;
    }

    @Override
    public int update(int id, Dette entity) {
        return 0;
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
