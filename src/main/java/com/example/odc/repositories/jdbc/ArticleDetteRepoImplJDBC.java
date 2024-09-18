package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.Article;
import com.example.odc.entities.ArticleDette;
import com.example.odc.repositories.ArticleDetteRepository;
import com.example.odc.repositories.ArticleRepository;
import com.example.odc.repositories.JdbcRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("jdbc")
@Repository
public class ArticleDetteRepoImplJDBC extends JdbcRepository<ArticleDette> implements ArticleDetteRepository {
    private final Database database;
    public ArticleDetteRepoImplJDBC(Database database) {
        super(database);
        this.database = database;
    }
}
