package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.Paiement;
import com.example.odc.repositories.JdbcIRepository;
import com.example.odc.repositories.PaiementIRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class PaiementRepoImplJDBC extends JdbcIRepository<Paiement> implements PaiementIRepository {
    private final Database database;
    public PaiementRepoImplJDBC(Database database) {
        super(database);
        resolveEntityType();
        this.database = database;
    }

    @Override
    public Collection<Paiement> findByDette(int id) {
        String sql = "SELECT * FROM paiements WHERE dette_id = ?";
        try{
            return Collections.singleton(database.executePreparedQuery(sql, Paiement.class, id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}