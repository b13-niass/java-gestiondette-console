package com.example.odc.repositories.collection;

import com.example.odc.entities.User;
import com.example.odc.repositories.CollectionRepository;
import com.example.odc.repositories.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Profile("collection")
@Repository
public class UserRepoImplCollection extends CollectionRepository<User> implements UserRepository {
    private final Collection collection;
    public UserRepoImplCollection(Collection<User> collection) {
        super(collection);
        this.collection = collection;
    }
    @Override
    public User findByLogin(String login) {
        return null;
    }
}
