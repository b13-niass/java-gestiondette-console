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
        List<Paiement> paiements = new ArrayList<>();
        String sql = "SELECT * FROM paiements WHERE dette_id = ?";

        try (ResultSet resultSet = database.executePreparedQuery(sql, id)) {
            while (resultSet.next()) {
                Paiement paiement = new Paiement();
                paiement.setId(resultSet.getInt("id"));
                paiement.setMontant(resultSet.getDouble("montant"));
                paiement.getDette().setId(resultSet.getInt("dette_id")); // Assuming you have this field in Paiement class
                paiements.add(paiement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return paiements;
    }
}