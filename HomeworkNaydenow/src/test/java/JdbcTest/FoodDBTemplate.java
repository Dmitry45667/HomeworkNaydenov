package JdbcTest;

import org.springframework.jdbc.core.JdbcTemplate;

public class FoodDBTemplate {

    private JdbcTemplate jdbcTemplate;

    public FoodDBTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Метод для добавления товара в таблицу FOOD
    public void insertFood(String foodName, String foodType, int foodExotic) {
        String insertQuery = "INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertQuery, foodName, foodType, foodExotic);
    }

    // Метод для просмотра содержимого таблицы FOOD
    public void displayFoodTable() {
        String selectQuery = "SELECT * FROM FOOD";
        jdbcTemplate.query(selectQuery, (rs) -> {
            while (rs.next()) {
                int foodId = rs.getInt("FOOD_ID");
                String foodName = rs.getString("FOOD_NAME");
                String foodType = rs.getString("FOOD_TYPE");
                int foodExotic = rs.getInt("FOOD_EXOTIC");
                System.out.println("ID: " + foodId +
                        " NAME: " + foodName +
                        " TYPE: " + foodType +
                        " isEXOTIC: " + foodExotic);
            }
        });
    }

    // Метод для удаления товара из таблицы FOOD
    public void deleteFoodById(int id) {
        String deleteGoodRequest = "DELETE FROM FOOD WHERE FOOD_ID = ?";

        int rowsAffected = jdbcTemplate.update(deleteGoodRequest, id);

        if (rowsAffected > 0) {
            System.out.println("Позиция успешно удалена из базы данных.");
        } else {
            System.out.println("Не удалось удалить позицию из базы данных. Возможно, товар с указанным ID не существует.");
        }
    }
}
