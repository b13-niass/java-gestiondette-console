package com.example.odc.services.impl;

import com.example.odc.entities.ArticleDette;
import com.example.odc.repositories.ArticleDetteIRepository;
import com.example.odc.services.ArticleDetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Component
public class ArticleDetteServiceImp implements ArticleDetteService {

    private final ArticleDetteIRepository repository;
     // Permet de spécifier le repository à utiliser en fonction du profile actuel (collection ou jdbc)
     public ArticleDetteServiceImp(@Qualifier("articleDetteRepoImplCollection") ArticleDetteIRepository repository){
        this.repository = repository;
    }
    @Override
    public Collection<ArticleDette> all() {
        return this.repository.findAll();
    }

    @Override
    public ArticleDette find(int id) {
        return this.repository.find(id);
    }

    @Override
    public int save(ArticleDette entity) {
        return this.repository.save(entity);
    }

    @Override
    public int delete(int id) {
        return this.repository.delete(id);
    }

    @Override
    public int update(int id, ArticleDette entity) {
        return this.repository.update(id, entity);
    }
}
