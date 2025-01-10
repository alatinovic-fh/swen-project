package org.example.application.data;

import org.example.application.entity.Card;
import org.example.application.util.PostgresConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardRepository {

    public List<Card> findCardsByUsername(String username) {
        String sql = "SELECT * FROM cards WHERE username = ?";
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, username);
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
            return cards;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Card> findDeckByUsername(String username) {
        String sql = """
                SELECT *
                FROM decks d
                LEFT JOIN cards c ON c.card_id = ANY (ARRAY[d.card_1, d.card_2, d.card_3, d.card_4])
                WHERE d.username = ?""";

        List<Card> cards = new ArrayList<>();
        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Card card = new Card(
                        resultSet.getString("card_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("damage")
                );

                cards.add(card);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cards;
    }

    public boolean updateDeck(List<String> cardIds, String username) {
        String sql = """
        INSERT INTO decks (username, card_1, card_2, card_3, card_4)
        VALUES (?, ?, ?, ?, ?)
        ON CONFLICT (username) 
        DO UPDATE SET 
            card_1 = EXCLUDED.card_1,
            card_2 = EXCLUDED.card_2,
            card_3 = EXCLUDED.card_3,
            card_4 = EXCLUDED.card_4;
    """;

        try (Connection connection = PostgresConfig.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            for (int i = 0; i < cardIds.size() && i < 4; i++) {
                statement.setString(i + 2, cardIds.get(i));
            }
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
