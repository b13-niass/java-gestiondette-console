package com.example.odc.config;

import com.example.odc.entities.Article;
import com.example.odc.entities.Client;
import com.example.odc.entities.Dette;
import com.example.odc.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class CollectionProvider {

    @Bean
    @Qualifier("myCollection")
    public <T> Collection<T> getCollection() {
        return new HashSet<>();
    }
}
