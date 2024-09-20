package com.example.odc.repositories.jdbc;

import com.example.odc.config.ApplicationContextProvider;
import com.example.odc.database.Database;
import com.example.odc.entities.ArticleDette;
import com.example.odc.entities.Dette;
import com.example.odc.entities.Paiement;
import com.example.odc.repositories.DetteIRepository;
import com.example.odc.repositories.JdbcIRepository;
import com.example.odc.repositories.PaiementIRepository;
import com.example.odc.services.ArticleDetteService;
import com.example.odc.services.ClientService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Component
public class DetteRepoImplJDBC extends JdbcIRepository<Dette> implements DetteIRepository {
    private final Database database;
    private final ClientService clientService;

    public DetteRepoImplJDBC(Database database, @Qualifier("clientServiceImp") ClientService clientService) {
        super(database);
        resolveEntityType();
        this.database = database;
        this.clientService = clientService;
    }

    @Override
    public int saveDette(Dette entity, Collection<ArticleDette> articleDettes, Optional<Paiement> paiementOptional) {
        // with SQL
        String sql = "INSERT INTO dettes (montant, client_id) VALUES (?,?) RETURNING id";
        try {
            Dette dette = database.executePreparedQuery(sql, Dette.class, entity.getMontant(), entity.getClient().getId());
            if (dette != null) {
                for (ArticleDette articleDette : articleDettes) {
                    articleDette.setDette(entity);
                    ApplicationContextProvider.getBean(ArticleDetteService.class).save(articleDette);
                }
                if (paiementOptional.isPresent()) {
                    paiementOptional.get().setDette(entity);
                    ApplicationContextProvider.getBean(PaiementIRepository.class).save(paiementOptional.get());
                }
                return entity.getId();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public double findMontantDu(int id) {
        String sql = "SELECT montant FROM dettes WHERE id = ?";
        try {
            Dette dette = database.executePreparedQuery(sql, Dette.class ,id);
            return dette.getMontant() - this.findMontantVerser(dette.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public double findMontantVerser(int id) {
        String sql = "SELECT SUM(montant) AS montant FROM paiements WHERE dette_id = ?";
        try{
            Paiement paiement = database.executePreparedQuery(sql, Paiement.class ,id);
            return paiement.getMontant(); // If null, return 0
        } catch (SQLException e) {
            System.err.println("Error fetching amount paid: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Collection<Dette> findByClient(int clientId) {
        String sql = "SELECT * FROM dettes WHERE client_id = ?";
        try {
            return Collections.singleton(database.executePreparedQuery(sql, Dette.class, clientId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}