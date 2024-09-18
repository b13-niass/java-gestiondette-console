package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.Article;
import com.example.odc.repositories.ArticleRepository;
import com.example.odc.repositories.JdbcRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Profile("jdbc")
@Repository
public class ArticleRepoImplJDBC extends JdbcRepository<Article> implements ArticleRepository {
    private final Database database;

    public ArticleRepoImplJDBC(Database database) {
        super(database);
        this.database = database;
    }

    @Override
    public Article findByLibelle(String libelle) {
        return null;
    }
}
