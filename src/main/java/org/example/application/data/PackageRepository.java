package org.example.application.data;

import org.example.application.entity.Card;
import org.example.application.util.PostgresConfig;

import java.sql.*;
import java.util.List;

public class PackageRepository {

    public boolean insertPackage(List<Card> cards, List<String> cardIds) {
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
                    return true;
                }
            }

        }catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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


    public boolean updatePackageToBought(String username){
        return false;
    }
    public int findCoinsByUsername(String username){
        return 0;
    }

    public boolean findAvailablePackage(){
        return false;
    }

    public boolean assignCardsToUser(String username, int packageId){
        return false;
    }

    public boolean updateCoins(String username){
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


    private boolean insertCard(Card card, int packageid){
        return false;
    }

}
