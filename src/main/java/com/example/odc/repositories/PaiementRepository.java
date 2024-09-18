package com.example.odc.repositories;

import com.example.odc.entities.Paiement;

import java.util.Collection;

public interface PaiementRepository extends Repository<Paiement>{
    public Collection<Paiement> findByDette(int id);
}
