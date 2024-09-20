package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.User;
import com.example.odc.repositories.JdbcIRepository;
import com.example.odc.repositories.UserIRepository;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class UserRepoImplJDBC extends JdbcIRepository<User> implements UserIRepository {
    private final Database database;
    public UserRepoImplJDBC(Database database) {
        super(database);
        resolveEntityType();
        this.database = database;
    }

    @Override
    public boolean authenticate(String username, String password) {
        String sql = "SELECT * FROM users WHERE login =? AND password =?";
        try {
            User user = this.database.executePreparedQuery(sql, User.class, username, password);
            return user != null;
        }catch (SQLException e){
            return false;
        }
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        String sql = "SELECT * FROM users WHERE login = ?";

        try{
            user = database.executePreparedQuery(sql, User.class, login);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

}