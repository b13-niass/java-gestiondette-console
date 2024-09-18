package com.example.odc.repositories.collection;

import com.example.odc.entities.Article;
import com.example.odc.repositories.ArticleRepository;
import com.example.odc.repositories.CollectionRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Profile("collection")

@Repository
public class ArticleRepoImplCollection extends CollectionRepository<Article> implements ArticleRepository {
    private final Collection collection;
    public ArticleRepoImplCollection(Collection<Article> collection) {
        super(collection);
         this.collection = collection;
    }

    @Override
    public Article findByLibelle(String libelle) {
        return null;
    }
}
