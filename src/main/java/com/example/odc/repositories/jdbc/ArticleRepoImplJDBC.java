package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.Article;
import com.example.odc.repositories.ArticleIRepository;
import com.example.odc.repositories.JdbcIRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

//@Profile("prod")
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

        try {
            ResultSet resultSet = database.executePreparedQuery(sql, libelle);

            if (resultSet.next()) {
                article = Article.builder()
                        .id(resultSet.getInt("id"))
                        .libelle(resultSet.getString("libelle"))
                        .prixUnitaire(resultSet.getDouble("prixUnitaire"))
                        .quantiteEnStock(resultSet.getInt("quantiteEnStock"))
                        .build();
            }
        } catch (SQLException e) {
            System.err.println("Error finding article by libelle: " + e.getMessage());
            e.printStackTrace();
        }

        return article;

    }


}
