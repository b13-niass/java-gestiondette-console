package com.example.odc.services;

import com.example.odc.entities.ArticleDette;
import com.example.odc.entities.Dette;
import com.example.odc.entities.Paiement;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public interface DetteService extends IService<Dette> {
    public double findMontantDu(int id);
    public double findMontantVerser(int id);
    public int save(Dette entity, Collection<ArticleDette> articleDettes, Optional<Paiement> paiementOptional);
    public Collection<Dette> findByClient(int id);
}
