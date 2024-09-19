package com.example.odc.repositories;

import com.example.odc.entities.Paiement;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Component
public interface PaiementIRepository extends IRepository<Paiement> {
    public Collection<Paiement> findByDette(int id);
}
