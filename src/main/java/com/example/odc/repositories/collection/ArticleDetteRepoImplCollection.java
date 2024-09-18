package com.example.odc.repositories.collection;

import com.example.odc.entities.ArticleDette;
import com.example.odc.repositories.ArticleDetteRepository;
import com.example.odc.repositories.CollectionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Profile("collection")
@Repository
public class ArticleDetteRepoImplCollection  extends CollectionRepository<ArticleDette> implements ArticleDetteRepository {
    private final Collection collection;
    public ArticleDetteRepoImplCollection(Collection<ArticleDette> collection) {
        super(collection);
         this.collection = collection;
    }
}
