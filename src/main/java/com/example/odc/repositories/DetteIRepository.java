package com.example.odc.repositories;

import com.example.odc.entities.ArticleDette;
import com.example.odc.entities.Dette;
import com.example.odc.entities.Paiement;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Component
public interface DetteIRepository extends IRepository<Dette> {
    public int saveDette(Dette entity, Collection<ArticleDette> articleDettes, Optional<Paiement> paiementOptional);
    public double findMontantDu(int id);
    public double findMontantVerser(int id);
    public Collection<Dette> findByClient(int id);
}
