package com.example.odc.repositories;

import com.example.odc.entities.ArticleDette;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Profile("collection")
@Component
public interface ArticleDetteIRepository extends IRepository<ArticleDette> {

}
