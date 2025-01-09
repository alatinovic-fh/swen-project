package org.example.application.data;

import org.example.application.entity.Card;
import org.example.application.util.PostgresConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//TODO Errorhandling maybe InternalServerError?

public class PackageRepository {

    public void insertPackage(List<Card> cards, List<String> cardIds) {
        String sql = "INSERT INTO packages (card_1, card_2, card_3, card_4, card_5, bought) values (?, ?, ?, ?, ?, ?)";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            for (int i = 0; i < 5; i++) {
                statement.setString(i + 1, cardIds.get(i));
            }
            statement.setBoolean(6, false);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {

                ResultSet keys = statement.getGeneratedKeys();
                if (keys.next()) {
                    int packageId = keys.getInt(1);

                    insertCards(cards, packageId);
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void insertCards(List<Card> cards, int packageId) {
        String sql  = "INSERT INTO cards (card_id, name, damage, package_id) values (?, ?, ?, ?)";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for(Card card : cards) {
                statement.setString(1, card.getId());
                statement.setString(2, card.getName());
                statement.setInt(3, card.getDamage());
                statement.setInt(4, packageId);
                statement.executeUpdate();
            }


        }catch(SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean assignCardsToUser(String username, int packageId) {
        String sql = "UPDATE cards SET username = ? WHERE package_id = ?";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, username);
            statement.setInt(2, packageId);
            statement.executeUpdate();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updatePackageToBought(int packageId) {
        String sql = "UPDATE packages SET bought = true WHERE id = ?";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setInt(1, packageId);
            statement.executeUpdate();
            return true;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int findCoinsByUsername(String username){
        String sql = "SELECT coins FROM users WHERE username = ?";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)){

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("coins");
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int findAvailablePackage() {
        String sql = "SELECT id FROM packages WHERE bought = false LIMIT 1";
        try (Connection connection = PostgresConfig.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return resultSet.getInt("id");
            }else{
                return 0;
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }


    public boolean updateCoins(String username){
        String sql = "UPDATE users SET coins = coins - 5 WHERE username = ?";

        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
            statement.executeUpdate();
            return true;

        }catch(SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cardExists(List<String> cardIds){
        String sql = "SELECT COUNT(*) FROM cards WHERE card_id IN (?, ?, ?, ?, ?)";

        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            for (int i = 0; i < cardIds.size(); i++) {
                statement.setString(i + 1, cardIds.get(i));
            }

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                // Return true if at least one card exists
                return resultSet.getInt(1) > 0;
            }

        } catch (SQLException e) {
            // TODO Errorhandling
        }

        return false;
    }


    public List<Card> getPackageCards(int packageId) {
        String sql = "SELECT * FROM cards WHERE package_id = ?";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, packageId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Card> cards = new ArrayList<>();
            while (resultSet.next()) {
                Card card = new Card(
                        resultSet.getString("card_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("damage")
                );

                cards.add(card);
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
