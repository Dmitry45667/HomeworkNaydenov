package JdbcTest;

import DBconfig.DataBaseConfig;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodDB {

    public DataBaseConfig dataBaseConfig;

    public FoodDB(DataBaseConfig dataBaseConfig) {
        this.dataBaseConfig = dataBaseConfig;
    }

    // Метод для добавления товара в таблицу FOOD
    public void insertFood(String foodName, String foodType, int foodExotic) {
        try (Connection connection = dataBaseConfig.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES (?, ?, ?)")) {

            ps.setString(1, foodName);
            ps.setString(2, foodType);
            ps.setInt(3, foodExotic);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Метод для просмотра содержимого таблицы FOOD
    public void displayFoodTable() {
        try (Connection connection = dataBaseConfig.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM FOOD");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                int foodId = rs.getInt("FOOD_ID");
                String foodName = rs.getString("FOOD_NAME");
                String foodType = rs.getString("FOOD_TYPE");
                int foodExotic = rs.getInt("FOOD_EXOTIC");
                System.out.println(" ID: " + foodId +
                        " NAME: " + foodName +
                        " TYPE: " + foodType +
                        " isEXOTIC: " + foodExotic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления товара из таблицы FOOD
    public void deleteGoodById(int id) {

            String deleteGoodRequest = "DELETE FROM FOOD WHERE FOOD_ID = ?";

            try (Connection connection = dataBaseConfig.getDataSource().getConnection();
                 PreparedStatement ps = connection.prepareStatement(deleteGoodRequest)) {

                ps.setInt(1, id);

                int rowsAffected = ps.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Позиция успешно удалена из базы данных.");
                } else {
                    System.out.println("Не удалось удалить позицию из базы данных. Возможно, товар с указанным ID не существует.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
