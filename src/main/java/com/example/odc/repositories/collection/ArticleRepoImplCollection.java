package com.example.odc.repositories.collection;

import com.example.odc.entities.*;
import com.example.odc.repositories.ArticleIRepository;
import com.example.odc.repositories.CollectionIRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ArticleRepoImplCollection extends CollectionIRepository<Article> implements ArticleIRepository {
    private final Collection<Article> collectionArticle;

    public ArticleRepoImplCollection(@Qualifier("myArticles") Collection<Article> collectionArticle) {
        super(collectionArticle);
        resolveEntityType();
        this.collectionArticle = collectionArticle;
    }

    @Override
    public Article findByLibelle(String libelle) {
        return this.collectionArticle.stream()
                .filter(article -> article.getLibelle().equals(libelle))
                .findFirst()
                .orElse(null);
    }
}
