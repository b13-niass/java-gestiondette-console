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
        try (ResultSet resultSet = database.executePreparedQuery(sql, entity.getMontant(), entity.getClient().getId())) {
            if (resultSet.next()) {
                entity.setId(resultSet.getInt("id"));
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
        try (ResultSet resultSet = database.executePreparedQuery(sql, id)) {
            if (resultSet.next()) {
                return resultSet.getDouble("montant");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    @Override
    public double findMontantVerser(int id) {
        String sql = "SELECT SUM(montant) AS total_verser FROM paiements WHERE dette_id = ?";
        try (ResultSet resultSet = database.executePreparedQuery(sql, id)) {
            if (resultSet.next()) {
                return resultSet.getDouble("total_verser");
            }
        } catch (SQLException e) {
            System.err.println("Error fetching amount paid: " + e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Collection<Dette> findByClient(int clientId) {
        String sql = "SELECT * FROM dettes WHERE client_id = ?";

        try (PreparedStatement ps = database.getConnection().prepareStatement(sql)) {
            ps.setInt(1, clientId); // Set client_id as a parameter

            try (ResultSet resultSet = ps.executeQuery()) {
                Collection<Dette> dettes = new ArrayList<>();

                while (resultSet.next()) {
                    Dette dette = Dette.builder()
                            .id(resultSet.getInt("id"))
                            .montant(resultSet.getDouble("montant"))
                            .client(clientService.find(resultSet.getInt("client_id"))) // Assume clientService is injected
                            .build();

                    dettes.add(dette);
                }

                return dettes;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while retrieving dettes for client: " + clientId, e);
        }
    }
}