package org.example.application.data;

import org.example.application.entity.User;
import org.example.application.exception.UserAlreadyExistsException;
import org.example.application.util.PostgresConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JpaUserRepository implements UserRepository {


    public JpaUserRepository() {
    }

    @Override
    public User save(User user) throws UserAlreadyExistsException {
        if (this.userExists(user.getUsername())) {
            throw new UserAlreadyExistsException("User "+ user.getUsername()+ " already exists");
        }
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            statement.executeUpdate(); //Update entry

        } catch (SQLException e) {
            // TODO Errorhandling
        }

        return user;
    }

    /**
     * This method checks if the user exists in the database
     *
     * @param username the username
     * @return true if present
     * @return false if not present
     */
    private boolean userExists(String username) {
        String sql = "SELECT EXISTS(SELECT 1 FROM users WHERE username = ?)";

        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Set Param
            statement.setString(1, username);

            // Execute query
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1); // Ergebnis der EXISTS-Abfrage
                }
            }
        } catch (SQLException e) {
            // TODO Errorhandling
        }

        return false;
    }

}
