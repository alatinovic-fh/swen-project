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
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

            statement.executeUpdate(); //Update entry

        } catch (SQLException e) {
        }

        return user;
    }

    @Override
    public User findByUsername(String username) {
        return null;
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

            // Parameter setzen
            statement.setString(1, username);

            // Abfrage ausführen
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getBoolean(1); // Ergebnis der EXISTS-Abfrage
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking if user exists: " + e.getMessage());
            e.printStackTrace();
        }

        // Rückgabe false, falls ein Fehler auftritt oder kein Ergebnis vorhanden ist
        return false;
    }

}
