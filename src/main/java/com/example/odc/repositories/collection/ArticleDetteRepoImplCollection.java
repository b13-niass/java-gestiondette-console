package com.example.odc.repositories.collection;

import com.example.odc.entities.*;
import com.example.odc.repositories.ArticleDetteIRepository;
import com.example.odc.repositories.CollectionIRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class ArticleDetteRepoImplCollection  extends CollectionIRepository<ArticleDette> implements ArticleDetteIRepository {
    private final Collection<ArticleDette> collectionArticleDette;


    public ArticleDetteRepoImplCollection(@Qualifier("myArticleDette") Collection<ArticleDette> collectionArticleDette) {
        super(collectionArticleDette);
        resolveEntityType();
         this.collectionArticleDette = collectionArticleDette;
    }
}
