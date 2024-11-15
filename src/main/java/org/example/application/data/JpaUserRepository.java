package org.example.application.data;

import org.example.application.entity.User;
import org.example.application.util.PostgresConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JpaUserRepository implements UserRepository {

    private final List<User> users;

    public JpaUserRepository() {
        users = new ArrayList<>();
    }

    @Override
    public User save(User user) {
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";

        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            int rowsAffected = statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exceptions (e.g., log the error)
        }

        return user;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

}
