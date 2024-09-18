package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.ArticleDette;
import com.example.odc.entities.Dette;
import com.example.odc.repositories.ArticleDetteRepository;
import com.example.odc.repositories.DetteRepository;
import com.example.odc.repositories.JdbcRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("jdbc")
@Repository
public class DetteRepoImplJDBC extends JdbcRepository<Dette> implements DetteRepository {
    private final Database database;
    public DetteRepoImplJDBC(Database database) {
        super(database);
        this.database = database;
    }

    @Override
    public double findMontantDu(int id) {
        return 0;
    }

    @Override
    public double findMontantVerser(int id) {
        return 0;
    }
}