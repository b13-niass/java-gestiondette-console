package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.ArticleDette;
import com.example.odc.repositories.ArticleDetteIRepository;
import com.example.odc.repositories.JdbcIRepository;
import org.springframework.stereotype.Component;

@Component
public class ArticleDetteRepoImplJDBC extends JdbcIRepository<ArticleDette> implements ArticleDetteIRepository {
    private final Database database;
    public ArticleDetteRepoImplJDBC(Database database) {
        super(database);
        resolveEntityType();
        this.database = database;
    }
}
