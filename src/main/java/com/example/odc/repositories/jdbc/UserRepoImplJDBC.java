package com.example.odc.repositories.jdbc;

import com.example.odc.database.Database;
import com.example.odc.entities.User;
import com.example.odc.enums.Role;
import com.example.odc.repositories.JdbcIRepository;
import com.example.odc.repositories.UserIRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
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
        String sql = "SELECT COUNT(*) FROM users WHERE login =? AND password =?";

        try (ResultSet resultSet = database.executePreparedQuery(sql, username, password)) {
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

    @Override
    public User findByLogin(String login) {
        User user = null;
        String sql = "SELECT * FROM users WHERE login = ?";

        try (ResultSet resultSet = database.executePreparedQuery(sql, login)) {
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setNom(resultSet.getString("nom"));
                user.setPrenom(resultSet.getString("prenom"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setRole(Role.valueOf(resultSet.getString("role"))); // Assuming User.Role is an enum
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }

}