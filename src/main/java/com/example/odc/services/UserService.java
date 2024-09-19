package com.example.odc.services;

import com.example.odc.entities.User;
import org.springframework.stereotype.Component;

@Component
public interface UserService extends IService<User> {
    public User findByLogin(String login);
    public boolean authenticate(String username, String password);
}
