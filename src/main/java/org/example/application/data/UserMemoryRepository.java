package org.example.application.data;

import org.example.application.dto.UserCredentials;
import org.example.application.dto.UserData;
import org.example.application.entity.User;
import org.example.application.exception.AuthenticationFailedException;
import org.example.application.exception.UserAlreadyExistsException;
import org.example.application.exception.UserNotFoundException;
import org.example.application.util.PostgresConfig;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;


/**
 * This method handles the sql-queries for the user
 *
 * @author Aleksandar Latinovic
 * */
public class UserMemoryRepository implements UserRepository {


    public UserMemoryRepository() {
        this.initTable();
    }

    /**
     *
     * This method inserts a User object into the database
     *
     * TODO Connection Pool?
     *
     * @param user
     * @return the user that has been added
     * @throws UserAlreadyExistsException if the username alreasy exists
     */
    @Override
    public UserCredentials save(UserCredentials user) throws UserAlreadyExistsException {
        if (this.userExists(user.getUsername())) {
            throw new UserAlreadyExistsException("User "+ user.getUsername()+ " already exists");
        }
        String sql = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());

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
     * @param username the username from the user trying to login
     * @return
     * @throws AuthenticationFailedException if the user does not exist or the password is incorrect
     */
    @Override
    public User findUserByUsername(String username) throws UserNotFoundException {
        if (!userExists(username)) {
            throw new UserNotFoundException("User not found");
        }

        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    User user = new User();
                    user.setUsername(resultSet.getString("username"));
                    user.setPassword(resultSet.getString("password"));
                    user.setFullName(resultSet.getString("fullname"));
                    user.setBio(resultSet.getString("bio"));
                    user.setImage(resultSet.getString("image"));
                    user.setCoins(resultSet.getInt("coins"));
                    user.setStats(resultSet.getInt("stats"));
                    // TODO Add deck and stack deserialization
                    return user;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error");
            // TODO Errorhandling
        }

        throw new UserNotFoundException("User not found");
    }

    @Override
    public UserData update(String searchedUser, UserData userData) throws UserNotFoundException {
        if(!userExists(searchedUser)){
            throw new UserNotFoundException("User not found");
        }
        String sql = "UPDATE users SET fullname = ?, bio = ?, image = ? WHERE username = ?";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, userData.getFullName());
            statement.setString(2, userData.getBio());
            statement.setString(3, userData.getImage());
            statement.setString(4, searchedUser);
            statement.executeUpdate();
            return userData;
        } catch (SQLException e) {
            // TODO Errorhandling
        }

        throw new UserNotFoundException("User not found");
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

    private void initTable(){
        try (Connection connection = PostgresConfig.getConnection();
             Statement statement = connection.createStatement();){
            //Load init.script users
            String sql = new String(Files.readAllBytes(Paths.get("./init.sql")));
            for(String sqlstatement : sql.split(";")){
                if(!sqlstatement.trim().isEmpty()){
                    statement.executeUpdate(sqlstatement.trim());
                }
            }

        } catch (SQLException e) {
            // TODO Errorhandling
        }catch (IOException e){
            // TODO Errorhandling
        }
    }

}
