package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.User;
import com.example.odc.repositories.JdbcRepository;
import com.example.odc.repositories.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("jdbc")
@Repository
public class UserRepoImplJDBC extends JdbcRepository<User> implements UserRepository {
    private final Database database;
    public UserRepoImplJDBC(Database database) {
        super(database);
        this.database = database;
    }


    @Override
    public User findByLogin(String login) {
        return null;
    }
}