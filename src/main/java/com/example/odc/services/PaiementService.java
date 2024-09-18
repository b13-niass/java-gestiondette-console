package com.example.odc.services;

import com.example.odc.entities.Paiement;

import java.util.Collection;

public interface PaiementService extends IService<Paiement> {
    public Collection<Paiement> findByDette(int id);

}
