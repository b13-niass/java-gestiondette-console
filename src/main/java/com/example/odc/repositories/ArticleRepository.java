package com.example.odc.repositories;

import com.example.odc.entities.Article;

public interface ArticleRepository extends Repository<Article> {
    public Article findByLibelle(String libelle);
}
