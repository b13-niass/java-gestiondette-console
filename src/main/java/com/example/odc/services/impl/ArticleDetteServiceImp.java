package com.example.odc.services.impl;

import com.example.odc.entities.ArticleDette;
import com.example.odc.repositories.ArticleDetteRepository;
import com.example.odc.repositories.ArticleRepository;
import com.example.odc.services.ArticleDetteService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class ArticleDetteServiceImp implements ArticleDetteService {
    private final ArticleDetteRepository repository;
    public ArticleDetteServiceImp(@Qualifier("collection") ArticleDetteRepository repository){
        this.repository = repository;
    }
    @Override
    public Collection<ArticleDette> all() {
        return List.of();
    }

    @Override
    public ArticleDette find(int id) {
        return null;
    }

    @Override
    public int save(ArticleDette entity) {
        return 0;
    }

    @Override
    public int delete(ArticleDette entity) {
        return 0;
    }

    @Override
    public int update(int id, ArticleDette entity) {
        return 0;
    }
}
