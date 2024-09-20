package com.example.odc.repositories.collection;

import com.example.odc.entities.*;
import com.example.odc.repositories.CollectionIRepository;
import com.example.odc.repositories.UserIRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserRepoImplCollection extends CollectionIRepository<User> implements UserIRepository {

    public final Collection<User> collectionUser;

    public UserRepoImplCollection(@Qualifier("myUsers") Collection<User> collectionUser) {
        super(collectionUser);
        resolveEntityType();
        this.collectionUser = collectionUser;
    }

    @Override
    public boolean authenticate(String username, String password) {
        User user = this.collectionUser.stream().filter(user1 -> user1.getLogin().equals(username) && user1.getPassword().equals(password)).findFirst().orElse(null);
        return user != null;
    }

    @Override
    public User findByLogin(String login) {
        return this.collectionUser.stream()
               .filter(user -> user.getLogin().equals(login))
               .findFirst()
               .orElse(null);
    }
}
