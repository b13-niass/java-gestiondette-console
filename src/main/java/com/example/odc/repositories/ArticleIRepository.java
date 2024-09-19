package com.example.odc.repositories;

import com.example.odc.entities.Article;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
public interface ArticleIRepository extends IRepository<Article> {
    public Article findByLibelle(String libelle);
}
