package org.example.application.data;

import org.example.application.entity.User;

import java.util.ArrayList;
import java.util.List;

public class JpaUserRepository implements UserRepository {

    private final List<User> users;

    public JpaUserRepository() {
        users = new ArrayList<>();
    }

    @Override
    public User save(User user) {
        // TODO: Add to database - postgres
        return user;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

}
