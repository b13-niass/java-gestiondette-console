package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.Article;
import com.example.odc.repositories.ArticleIRepository;
import com.example.odc.repositories.JdbcIRepository;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ArticleRepoImplJDBC extends JdbcIRepository<Article> implements ArticleIRepository {
    private final Database database;

    public ArticleRepoImplJDBC(Database database) {
        super(database);
        resolveEntityType();
        this.database = database;
    }

    @Override
    public Article findByLibelle(String libelle) {
        Article article = null;
        String sql = "SELECT * FROM articles WHERE libelle = ?";
        Article a = Article.builder().libelle(libelle).build();
        try {
            article = database.executePreparedQuery(sql, Article.class, a);
        } catch (SQLException e) {
            System.err.println("Error finding article by libelle: " + e.getMessage());
            e.printStackTrace();
        }
        return article;
    }

}
