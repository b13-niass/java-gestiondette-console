package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.Paiement;
import com.example.odc.repositories.JdbcRepository;
import com.example.odc.repositories.PaiementRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Profile("jdbc")
@Repository
public class PaiementRepoImplJDBC extends JdbcRepository<Paiement> implements PaiementRepository {
    private final Database database;
    public PaiementRepoImplJDBC(Database database) {
        super(database);
        this.database = database;
    }

    @Override
    public Collection<Paiement> findByDette(int id) {
        return List.of();
    }
}