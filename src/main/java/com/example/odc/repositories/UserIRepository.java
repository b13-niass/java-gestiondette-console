package com.example.odc.repositories;

import com.example.odc.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Component
public interface UserIRepository extends IRepository<User> {
    public boolean authenticate(String username, String password);
    public User findByLogin(String login);
}
