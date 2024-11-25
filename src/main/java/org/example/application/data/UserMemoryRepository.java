package org.example.application.data;

import org.example.application.entity.User;
import org.example.application.exception.AuthenticationFailedException;
import org.example.application.exception.UserAlreadyExistsException;
import org.example.application.util.PostgresConfig;

import java.sql.*;


/**
 * This method handles the sql-queries for the user
 *
 * @author Aleksandar Latinovic
 * */
public class UserMemoryRepository implements UserRepository {


    public UserMemoryRepository() {
    }

    /**
     *
     * This method inserts a User object into the database
     *
     * @param user
     * @return the user that has been added
     * @throws UserAlreadyExistsException if the username alreasy exists
     */
    @Override
    public User save(User user) throws UserAlreadyExistsException {
        if (this.userExists(user.getUsername())) {
            throw new UserAlreadyExistsException("User "+ user.getUsername()+ " already exists");
        }
        String sql = "INSERT INTO users (username, password, coins) VALUES (?, ?, ?)";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setInt(3, user.getCoins());

            statement.executeUpdate();

        } catch (SQLException e) {
            // TODO Errorhandling
        }

        return user;
    }

    /**
     * This method checks if the given password matches to the user
     * in the database
     *
     * @param user the user trying to login
     * @return
     * @throws AuthenticationFailedException if the user does not exist or the password is incorrect
     */
    @Override
    public boolean verify(User user) throws AuthenticationFailedException {
        if (!userExists(user.getUsername())) {
            throw new AuthenticationFailedException("Login failed");
        }

        String sql = "SELECT password FROM users WHERE username = ?";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String storedPassword = resultSet.getString("password");

                    if (storedPassword.equals(user.getPassword())) {
                        return true;
                    }
                }
            }
        } catch (SQLException e) {
            // TODO Errorhandling
        }

        throw new AuthenticationFailedException("Login failed");
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
