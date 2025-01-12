package com.example.odc.services;

import com.example.odc.entities.Paiement;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public interface PaiementService extends IService<Paiement> {
    public Collection<Paiement> findByDette(int id);

}
